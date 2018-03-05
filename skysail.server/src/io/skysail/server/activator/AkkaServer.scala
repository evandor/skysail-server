package io.skysail.server.activator

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow
import akka.osgi.ActorSystemActivator
import akka.stream.ActorMaterializer
import akka.util.Timeout
import domino.DominoActivator
import domino.bundle_watching.BundleWatcherEvent.{AddingBundle, ModifiedBundle, RemovedBundle}
import domino.capsule.Capsule
import domino.service_watching.ServiceWatcherContext
import domino.service_watching.ServiceWatcherEvent.{AddingService, ModifiedService, RemovedService}
import io.skysail.api.metrics.{CounterMetric, Metrics}
import io.skysail.api.security.AuthenticationService
import io.skysail.api.ui.Client
import io.skysail.server.actors.{ApplicationsActor, BundlesActor}
import io.skysail.server.app.{ApplicationProvider, BackendApplication, RootApplication}
import io.skysail.server.app.BackendApplication._
import io.skysail.server.metrics.SimpleMetrics
import io.skysail.server.routes.{RoutesCreator, RoutesTracker}
import io.skysail.server.{Constants, RoutesCreatorTrait, SystemPropertiesCommand}
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration.DurationInt


case class ServerConfig(port: Integer, binding: String, conf: Map[String, Any])

object AkkaServer {
  val metricsImpls: ListBuffer[Metrics] = scala.collection.mutable.ListBuffer[Metrics]()
  val clients: ListBuffer[Client] = scala.collection.mutable.ListBuffer[Client]()
  val simpleMetrics = new SimpleMetrics()
  metricsImpls += simpleMetrics
}

/**
  * The skysail server OSGi activator, starting all skysail server application logic.
  *
  *
  */
class AkkaServer extends DominoActivator {

  SLF4JBridgeHandler.removeHandlersForRootLogger
  SLF4JBridgeHandler.install

  private var log = LoggerFactory.getLogger(this.getClass)

  private implicit var actorSystem: ActorSystem = _

  private val defaultPort = 8080
  private val defaultBinding = "0.0.0.0"
  private val defaultAuthentication = "HTTP_BASIC"

  private var futureBinding: Future[Http.ServerBinding] = _
  private var applicationsActor: ActorRef = _
  private var bundlesActor: ActorRef = _
  private var serverConfig = ServerConfig(defaultPort, defaultBinding, Map())
  private var routesTracker: RoutesTracker = _
  private var routesCreator: RoutesCreator = _
  private var serverRestartsCounter = CounterMetric(this.getClass, "server.restarts")
  private var rootApplication: Option[RootApplication] = None

  private class AkkaCapsule(bundleContext: BundleContext) extends ActorSystemActivator with Capsule {

    override def start(): Unit = {
      start(bundleContext)
    }

    override def stop(): Unit = stop(bundleContext)

    def configure(osgiContext: BundleContext, system: ActorSystem): Unit = {
      log info "Registering Actor System as Service."
      registerService(osgiContext, system)

      log info s"ActorSystem [${system.name}] initialized."
      actorSystem = system

      log info s"creating ApplicationsActor... "
      applicationsActor = system.actorOf(Props[ApplicationsActor], Constants.APPLICATIONS_ACTOR_NAME)
      log info s"created ApplicationsActor with path ${applicationsActor.path}"

      log info s"creating BundlesActor"
      bundlesActor = system.actorOf(Props(new BundlesActor(bundleContext)), Constants.BUNDLES_ACTOR_NAME)
      log info s"created BundlesActor with path ${bundlesActor.path}"

      log info "new RoutesTracker"
      routesTracker = new RoutesTracker(actorSystem)

      log info "new RoutesCreator"
      routesCreator = new RoutesCreator(actorSystem)
    }

    override def getActorSystemName(context: BundleContext): String = "SkysailActorSystem"
  }

  whenBundleActive({

    log info ""
    log info s"bundle skysail.server became active"
    addCapsule(new AkkaCapsule(bundleContext))

    //Kamon.start()
    //serverRestartsCounter = Kamon.metrics.counter("server.restarts")

    watchServices[ApplicationProvider] {
      case AddingService(service, context) => addApplicationProvider(service, context)
      case ModifiedService(service, context) => log info s"Service '$service' modified"; addApplicationProvider(service, context)
      case RemovedService(service, _) => removeApplicationProvider(service)
    }

    watchServices[AuthenticationService] {
      case AddingService(service, context) => {
        //routesTracker.setAuthentication(service)
        routesCreator.setAuthentication(service)
        routesCreator.providesService[RoutesCreatorTrait]
      }
      case ModifiedService(service, _) => //log info s"Service '$service' modified"
      case RemovedService(service, _) => removeAuthenticationService(service)
    }

    watchServices[Metrics] {
      case AddingService(service, context) => AkkaServer.metricsImpls += service
      case ModifiedService(service, _) =>
      case RemovedService(service, _) => AkkaServer.metricsImpls -= service
    }

    watchServices[Client] {
      case AddingService(client, context) => AkkaServer.clients += client; updateApp()
      case ModifiedService(client, _) =>
      case RemovedService(client, _) => AkkaServer.clients -= client; updateApp()
    }

    watchBundles {
      case AddingBundle(b, context) => bundlesActor ! BundlesActor.CreateBundleActor(b)
      case ModifiedBundle(b, _) =>
      case RemovedBundle(b, _) => log info s"Bundle ${b.getSymbolicName} removed"
    }

    new SystemPropertiesCommand().providesService[Object](
      "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
      "osgi.command.function" -> "systemProperties"
    )

    AkkaServer.simpleMetrics.providesService[Object](
      "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
      "osgi.command.function" -> "dumpMetrics"
    )

    whenConfigurationActive("server") { conf =>
      log info s"received configuration for 'server': $conf"
      val port = Integer.parseInt(conf.getOrElse("port", defaultPort.toString).asInstanceOf[String])
      var binding = conf.getOrElse("binding", defaultBinding).asInstanceOf[String]
      //var authentication = conf.getOrElse("authentication", defaultAuthentication).asInstanceOf[String]
      serverConfig = ServerConfig(port, binding, conf)

      log info "creating RootApplication"
      rootApplication = Some(new RootApplication(bundleContext, routesCreator, actorSystem, conf))
      rootApplication.get.providesService[ApplicationProvider]
    }

  })

  private def addApplicationProvider(appInfoProvider: ApplicationProvider, ctx: ServiceWatcherContext[_]): Unit = {
    log info s"adding ApplicationProvider: $appInfoProvider; $ctx"
    createApplicationActor(appInfoProvider)
    routesTracker.addRoutesFor(appInfoProvider)
    restartServer(routesTracker.routes())
  }

  private def removeApplicationProvider(appInfoProvider: ApplicationProvider): Unit = {
    routesTracker.removeRoutesFrom(appInfoProvider)
    restartServer(routesTracker.routes())
  }

  private def removeAuthenticationService(authenticationService: AuthenticationService): Unit = {
    // routesTracker.setAuthentication(null)
    // TODO routesCreator?
  }

  private def startServer(routes: List[Route]) = {
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    log info s"(re)starting server with binding ${serverConfig.binding}:${serverConfig.port} with #${routesTracker.routes.size} routes."
    AkkaServer.metricsImpls.foreach(_.inc(serverRestartsCounter))
    routes.size match {
      case 0 => log warn "Akka HTTP Server not started as no routes are defined"; null
      case 1 => Http(actorSystem).bindAndHandle(routes.head, serverConfig.binding, serverConfig.port)
      case _ => Http(actorSystem).bindAndHandle(routes.reduce((a, b) => a ~ b), serverConfig.binding, serverConfig.port)
    }
  }

  private def restartServer(routes: List[Route]): Unit = {
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    if (futureBinding != null) {
      implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
      futureBinding.flatMap(_.unbind()).onComplete { _ => futureBinding = startServer(routes) }
    } else {
      futureBinding = startServer(routes)
    }
  }

  private def createApplicationActor(appInfoProvider: ApplicationProvider): Unit = {
    if (appInfoProvider == null) {
      log warn "provided ApplicationProvider was null!"
    } else {
      implicit val askTimeout: Timeout = 6.seconds
      val appsActor = BackendApplication.getApplicationsActor(actorSystem)
      val appClass = appInfoProvider.getClass.asInstanceOf[Class[BackendApplication]]
      val appModel = appInfoProvider.appModel()
      val application = appInfoProvider.application()
      appsActor ! CreateApplicationActor(appClass, appModel, application, appInfoProvider.bundleContext)
    }
  }

  private def updateApp(): Unit = {
    if (rootApplication.isDefined) {
      rootApplication.get.setClients(AkkaServer.clients.toList)
    }
  }


}
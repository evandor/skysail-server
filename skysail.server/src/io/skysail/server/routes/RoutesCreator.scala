package io.skysail.server.routes

import java.lang.annotation.Annotation
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.ws.{BinaryMessage, Message, TextMessage}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.pattern.ask
import akka.stream.scaladsl.{Flow, Source}
import akka.util.Timeout
import io.skysail.api.security.AuthenticationService
import io.skysail.domain.SkysailResource
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.routes.{RouteMapping, RouteMappingI}
import io.skysail.server.{Constants, RoutesCreatorTrait}
import io.skysail.server.TunnelDirectives._
import io.skysail.server.actors.{BundleActor, BundlesActor}
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.Bundle
import org.osgi.framework.wiring.{BundleCapability, BundleWiring}
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

object RoutesCreator {

  def apply(system: ActorSystem): RoutesCreator = new RoutesCreator(system)

  def getApplicationActorSelection(system: ActorSystem, name: String): ActorSelection = {
    val applicationActorPath = "/user/" + Constants.APPLICATIONS_ACTOR_NAME + "/" + name
    system.actorSelection(applicationActorPath)
  }
}

class RoutesCreator(system: ActorSystem) extends RoutesCreatorTrait {

  private val log = LoggerFactory.getLogger(this.getClass)

  log info s"instanciating new $this.getClass.getName"

  var authentication: AuthenticationService = _

  private val counter = new AtomicInteger(0)

  implicit val timeout: Timeout = 3.seconds

  var docClassloader: ClassLoader = _

  def getClientClassloader2(): ClassLoader = {

    val capabilitiesFuture = (BackendApplication.getBundlesActor(system) ? BundlesActor.GetCapabilities()).mapTo[Map[Long, List[BundleCapability]]]
    val capabilities = Await.result(capabilitiesFuture, 3.seconds)

    val bundleIdsWithClientCapabilities2 = capabilities.filter {
      entry => entry._2.exists { cap => Constants.CLIENT_CAPABILITY.equals(cap.getNamespace) }
    }.keys

    if (bundleIdsWithClientCapabilities2.nonEmpty) {
      val clientClFuture = (BackendApplication.getBundleActor(system, bundleIdsWithClientCapabilities2.head) ? BundleActor.GetClassloader()).mapTo[ClassLoader]
      val r = Await.result(clientClFuture, 3.seconds)
      r
    } else null
  }

  def createRoute(mapping: RouteMappingI[_, _], appProvider: ApplicationProvider): Route = {
    val appRoute = appProvider.appModel().appRoute
    log info s" >>> creating route from [${appProvider.appModel().appPath()}]${mapping.path} -> " +
      s"${mapping.resourceClass.getSimpleName}[${mapping.getEntityType()}]"

    val route: Route =
      staticResources() ~
        matcher(mapping, appProvider) ~
        clientPath() ~
        docPath() ~
        indexPath() ~
        websocketPath()

    def myRejectionHandler =
      RejectionHandler.newBuilder()
        .handle { case MissingCookieRejection(cookieName) =>
          complete(HttpResponse(BadRequest, entity = "No cookies, no service!!!"))
        }
        .handle { case AuthorizationFailedRejection =>
          complete((Forbidden, "You're out of your depth!"))
        }
        .handle { case ValidationRejection(msg, _) =>
          complete((InternalServerError, "That wasn't valid! " + msg))
        }
        .handleAll[MethodRejection] { methodRejections =>
        val names = methodRejections.map(_.supported.name)
        complete((MethodNotAllowed, s"Can't do that! Supported: ${names mkString " or "}!"))
      }
        .result()

    handleRejections(myRejectionHandler) {
      /*if (appProvider.nativeRoute().isDefined)
        appProvider.nativeRoute().get ~ route
      else*/
        route
    }
  }

  private def indexPath(): Route = {
    path("c2") {
      get {
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader2())
      }
    } ~
      path("v2") {
        get {
          getFromResource("assets/index.html", ContentTypes.`text/html(UTF-8)`, this.getClass.getClassLoader)
        }
      } ~
      path("c4") {
        parameterMap { map =>
          println("MAP: " + map)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hier we are"))
        }
      }
  }

  def greeter: Flow[Message, Message, Any] =
    Flow[Message].mapConcat {
      case tm: TextMessage =>
        TextMessage(Source.single("Hello ") ++ tm.textStream ++ Source.single("!")) :: Nil
      case bm: BinaryMessage =>
        // ignore binary messages but drain content to avoid the stream being clogged
        // bm.dataStream.runWith(Sink.ignore)
        Nil
    }

  private def websocketPath(): Route = {
    path("websocket") {
      handleWebSocketMessages(greeter)
    }
  }

  private def clientPath(): Route = {
    pathPrefix("client") {
      get {
        getFromResourceDirectory("client", getClientClassloader2)
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader2)
      }
    }
  }

  private def docPath(): Route = {
    pathPrefix("ddd") {
      get {
        //getFromResourceDirectory("assets/html5", getDocClassloader)
        getFromResource("assets/html5/meta.html", ContentTypes.`text/html(UTF-8)`, getDocClassloader)
      }
    }
  }

  private def staticResources(): Route = {
    pathPrefix("static") {
      get {
        //implicit val classloader: ClassLoader = clientClassloader2() /*classOf[AkkaServer].getClassLoader*/
        getFromResource("application.conf", ContentTypes.`application/json`, getClientClassloader2)
      }
    } ~
      pathPrefix("client") {
        get {
          getFromResourceDirectory("client", getClientClassloader2)
        }
      }
  }

  private def getMatcher(path: String) = {
    val trimmed = path.trim()
    if (trimmed.startsWith("/")) PathMatcher(trimmed.substring(1)) else PathMatcher(trimmed)
  }

  private def matcher(mapping: RouteMappingI[_, _], appProvider: ApplicationProvider): Route = {

    val getAnnotation = requestAnnotationForGet(mapping.resourceClass)

    val parameterType = mapping.getPathMatcherParameterType()

    parameterType match {
      case t if t =:= typeOf[Unit] =>
        pathPrefix(mapping.pathMatcher.asInstanceOf[PathMatcher[Unit]]) {
          handle(mapping, appProvider)
        }
      case t if t =:= typeOf[Tuple1[String]] =>
        pathPrefix(mapping.pathMatcher.asInstanceOf[PathMatcher[Tuple1[String]]]) { urlParameter =>
          handle(mapping, appProvider, List(urlParameter))
        }
      case m: Any =>
        (get | post | put | delete) {
          extractRequestContext { ctx =>
            complete {
              log info s"Ctx: $ctx"
              log info s"First: $m"
              "error1"
            }
          }
        }

    }

  }

  private def handle(mapping: RouteMappingI[_, _], appProvider: ApplicationProvider, pathParameter: List[String] = List()) = {
    parameters('_method.?) { tunnelMethod =>
      authenticationDirective(authentication) { username =>
        handleOptionalTunnelMethod(tunnelMethod) {
          get {
            extractRequestContext {
              ctx => routeWithUnmatchedPath2(ctx, mapping, appProvider, pathParameter)
            }
          } ~
            post {
              extractRequestContext {
                ctx =>
                  routeWithUnmatchedPath2(ctx, mapping, appProvider, pathParameter)
              }
            } ~
            put {
              extractRequestContext {
                ctx =>
                  routeWithUnmatchedPath2(ctx, mapping, appProvider, pathParameter)
              }
            }
        }
      }
    }
  }

  private def routeWithUnmatchedPath2(
                                       ctx: RequestContext,
                                       mapping: RouteMappingI[_, _],
                                       appProvider: ApplicationProvider,
                                       urlParameter: List[String] = List()): Route

  = {
    extractUnmatchedPath { unmatchedPath =>
      val applicationActor = RoutesCreator.getApplicationActorSelection(system, appProvider.getClass.getName)
      //val clazz = mapping.resourceClass
      val resourceInstance = mapping.resourceClass.newInstance()
      val processCommand = ProcessCommand(ctx, mapping, appProvider.application(), urlParameter, unmatchedPath)

      resourceInstance.createRoute(applicationActor, processCommand)(system)
    }
  }

  def setAuthentication(a: AuthenticationService): Unit = {
    log info s"setting authenticataion to $a"
    authentication = a
  }

  private def authenticationDirective(auth: AuthenticationService): Directive1[String]

  = auth.directive()

  private def requestAnnotationForGet(cls: Class[_ <: SkysailResource[_, _]]): Option[Annotation] = {
    try {
      val getMethod = cls.getMethod("get", classOf[ActorRef], classOf[ClassTag[_]])
      //Some(getMethod.getAnnotation(classOf[AuthorizeByRole]))
      None
    } catch {
      case e: Throwable => None //log.error(e.getMessage(), e)
    }
  }

  // private def getClientClassloader = clientClassloader

  private def getDocClassloader = {
    if (docClassloader == null) {
      val bundlesActor = BackendApplication.getBundlesActor(system)
      val docBundle: Future[Bundle] = (bundlesActor ? BundlesActor.GetBundleBySymbolicName("skysail.server.doc")).mapTo[Bundle]
      val b: Bundle = Await.result(docBundle, 3.seconds)
      docClassloader = b.adapt(classOf[BundleWiring]).getClassLoader
    }

    docClassloader
  }

}


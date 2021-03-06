package io.skysail.server.app

import java.util.ResourceBundle

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives.pathPrefix
import akka.http.scaladsl.server.RouteConcatenation._
import akka.http.scaladsl.server.{Route, _}
import io.skysail.domain.SkysailResource
import io.skysail.domain.app.{ApiVersion, ApplicationApi}
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.{RouteMapping, RouteMappingI}
import io.skysail.server.{Constants, RoutesCreatorTrait}
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object BackendApplication {

  case class InitResourceActorChain(requestContext: RequestContext, cls: Class[_ <: SkysailResource[_, _]])

  case class CreateApplicationActor(cls: Class[_ <: BackendApplication], appModel: ApplicationModel, application: ApplicationApi, bundleContext: BundleContext)

  case class DeleteApplicationActor(cls: Class[_ <: BackendApplication])

  def getApplicationsActor(system: ActorSystem): ActorRef = {
    val applicationsActorPath = "/user/" + Constants.APPLICATIONS_ACTOR_NAME
    val applicationsActorSelection = system.actorSelection(applicationsActorPath)
    val r = applicationsActorSelection.resolveOne(2.seconds)
    Await.result(r, 1.seconds)
  }

  def AgetApplicationActorSelection(system: ActorSystem, name: String): ActorSelection = {
    val applicationActorPath = "/user/" + Constants.APPLICATIONS_ACTOR_NAME + "/" + name
    system.actorSelection(applicationActorPath)
  }

  def getBundlesActor(system: ActorSystem): ActorSelection = {
    system.actorSelection("/user/" + Constants.BUNDLES_ACTOR_NAME)
  }

  def getBundleActor(system: ActorSystem, bundleId: Long): ActorSelection = {
    //println(new PrivateMethodExposer(theSystem)('printTree)())
    val actorSelection = "/user/" + Constants.BUNDLES_ACTOR_NAME + "/" + bundleId.toString
    println("searching for actorSelection " + actorSelection)
    system.actorSelection(actorSelection)
  }

}

/**
  * Bundles can provide a class implementing a BackendApplication to provide endpoints
  * for a bounded context.
  *
  * A BackendApplication has a name and a version, and it defines a router, which basically
  * is an akka http Route which captures all possible requests to this application.
  *
  * The endpoints will be available under "https://<host>:<port>/<application name>/<appication version>".
  * If the verision is set to null, <application version> will be omitted. The default version is "v1".
  *
  * @param bundleContext
  * @param routesCreator
  * @param system
  */
abstract class BackendApplication(
                                   val bundleContext: BundleContext,
                                   routesCreator: RoutesCreatorTrait,
                                   system: ActorSystem) extends ApplicationProvider with ApplicationApi {

  private val log = LoggerFactory.getLogger(classOf[BackendApplication])

  val appModel = ApplicationModel(name, version, desc)

  def name: String

  def desc: String

  def version = ApiVersion(1)

  /**
    * determines the complete list of endpoints for this application.
    *
    * The routeMappings are evaluated, and the nativeRoute (if not null)
    * is added as well.
    *
    * @return the (concatenated) route definition for the whole application
    */
  lazy val router: Route = {
    val valueList: List[Route] = routesMappings.map { prt =>
      appModel.addResourceModel(prt)
      routesCreator.createRoute(prt, this)
    }
    val fullList: List[Route] = if (optionalNativeRoute().isDefined) {
      optionalNativeRoute().get :: valueList
    } else {
      valueList
    }
    fullList.size match {
      case 0 => log warn "no routes are defined"; null
      case 1 => fullList.head
      case _ => fullList.reduce((a, b) => a ~ b)
    }
  }

  /**
    * overwrite in concrete implementations to provide the list of http endpoints for this
    * application.
    *
    * @return a list of mappings between paths and resource class definitions.
    */
  def routesMappings: List[RouteMappingI[_, _]]

  /**
    * Can be overwritten to add an akka http route directly.
    *
    * @return an akka http route or null
    */
  def nativeRoute(): Route = null

  def application(): BackendApplication = this

  def getResourceBundles: List[ResourceBundle] = List[ResourceBundle]()

  override final def optionalNativeRoute(): Option[Route] = {
    val or = nativeRoute()
    if (or != null) {
      log info s" >>> adding route to $appModel"
      Some(pathPrefix(appModel.appRoute) {
        or
      })
    } else None
  }

}


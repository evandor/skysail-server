package io.skysail.server.app

import java.util.concurrent.atomic.AtomicInteger
import java.util.{Collections, ResourceBundle}

import akka.actor.{ActorRef, ActorSelection, ActorSystem, Props}
import akka.http.scaladsl.server.Directives.pathPrefix
import akka.http.scaladsl.server.{Route, _}
import io.skysail.domain.SkysailResource
import io.skysail.domain.app.{ApiVersion, ApplicationApi}
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.Constants
import io.skysail.server.routes.RoutesCreator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object SkysailApplication {

  case class InitResourceActorChain(requestContext: RequestContext, cls: Class[_ <: SkysailResource[_,_]])

  case class CreateApplicationActor(cls: Class[_ <: SkysailApplication], appModel: ApplicationModel, application: ApplicationApi, bundleContext: BundleContext)

  case class DeleteApplicationActor(cls: Class[_ <: SkysailApplication])

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

abstract class SkysailApplication(
                                   name: String,
                                   val apiVersion: ApiVersion,
                                   val bundleContext: BundleContext,
                                   system: ActorSystem,
                                   description: String) extends ApplicationProvider with ApplicationApi {

  private val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  def this(name: String, bc: BundleContext, system: ActorSystem, desc: String) = this(name, ApiVersion(1), bc, system, desc)

  val appModel = ApplicationModel(name, apiVersion, description)

  private val routesCreator = RoutesCreator(system)

  def router: Route = {
    val  valueList: List[Route] = routesMappings.map { prt => routesCreator.createRoute(prt, this) }
    null
  }

  def routesMappings: List[RouteMapping[_,_]]

  val routes: List[RouteMapping[_,_]] = {
    routesMappings.foreach(m => {
      appModel.addResourceModel(m)
    })
    routesMappings // ++ List(RouteMapping("/_model", classOf[ModelResource]))
  }

  var appRoutes: List[Route] = _

  def optionalRoute(): Route = null

  def application(): SkysailApplication = this

  def getResourceBundles: List[ResourceBundle] = List[ResourceBundle]()

  override final def nativeRoute(): Option[Route] = {
    val or = optionalRoute()
    if (or != null) {
      log info s" >>> adding route to $appModel"
      Some(pathPrefix(appModel.appRoute) {
        or
      })
    } else None
  }

}


package io.skysail.server.app

import java.util.ResourceBundle

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives.pathPrefix
import akka.http.scaladsl.server.RouteConcatenation._
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

abstract class BackendApplication(
                                   val bundleContext: BundleContext,
                                   system: ActorSystem)
  extends ApplicationProvider with ApplicationApi {

  private val log = LoggerFactory.getLogger(classOf[BackendApplication])

  val appModel = ApplicationModel(name, version, desc)

  private val routesCreator = RoutesCreator(system)

  def name: String

  def desc: String

  def version = ApiVersion(1)

  def router: Route = {
    val valueList: List[Route] = routesMappings.map { prt =>
      appModel.addResourceModel(prt)
      routesCreator.createRoute(prt, this)
    }
    val fullList: List[Route] = if (nativeRoute().isDefined) {
      nativeRoute().get :: valueList
    } else {
      valueList
    }
    fullList.size match {
      case 0 => log warn "no routes are defined"; null
      case 1 => fullList.head
      case _ => fullList.reduce((a, b) => a ~ b)
    }
  }

  def routesMappings: List[RouteMapping[_, _]]

//  val routes: List[RouteMapping[_, _]] = {
//    routesMappings.foreach(m => {
//      appModel.addResourceModel(m)
//    })
//    routesMappings // ++ List(RouteMapping("/_model", classOf[ModelResource]))
//  }

  //var appRoutes: List[Route] = _

  def optionalRoute(): Route = null

  def application(): BackendApplication = this

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


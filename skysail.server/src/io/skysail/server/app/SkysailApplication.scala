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
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object SkysailApplication {

  case class InitResourceActorChain(requestContext: RequestContext, cls: Class[_ <: SkysailResource[_]])

  case class CreateApplicationActor(cls: Class[_ <: SkysailApplication], appModel: ApplicationModel, application: ApplicationApi, bundleContext: BundleContext)

  case class DeleteApplicationActor(cls: Class[_ <: SkysailApplication])

  def getApplicationsActor(system: ActorSystem): ActorRef = {
    val applicationsActorPath = "/user/" + Constants.APPLICATIONS_ACTOR_NAME
    //log debug s"searching applicationsActor @ path '${applicationsActorPath}' in system ${system}"
    val applicationsActorSelection = system.actorSelection(applicationsActorPath)
    val r = applicationsActorSelection.resolveOne(2.seconds)
    Await.result(r, 1.seconds)
  }

  def getApplicationActorSelection(system: ActorSystem, name: String): ActorSelection = {
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
                                   description: String) extends ApplicationProvider with ApplicationApi {

  private val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  def this(name: String, bundleContext: BundleContext, description: String) =
    this(name, ApiVersion(1), bundleContext, description)

  val appModel = ApplicationModel(name, apiVersion, description)

  def routesMappings: List[RouteMapping[_,_]]

  var actorRefsMap = Map.empty[String, ActorRef]

  val routes: List[RouteMapping[_,_]] = {
    routesMappings.foreach(m => {
      appModel.addResourceModel(m)
    })
    routesMappings // ++ List(RouteMapping("/_model", classOf[ModelResource]))
  }

  def application(): SkysailApplication = this

  var host = "localhost"

  def getHost: String = host

  var appRoutes: List[Route] = _
  var system: ActorSystem = _
  val cnt = new AtomicInteger(0)

  def getResourceBundles: List[ResourceBundle] = List[ResourceBundle]()

  def getTemplatePaths[T](x$1: Class[T]): java.util.List[String] = {
    Collections.emptyList()
  }

  def getSkysailApplication: SkysailApplication = this

  override final def route(): Option[Route] = {
    val or = optionalRoute()
    if (or != null) {
      log info s" >>> adding route to $appModel"
      Some(pathPrefix(appModel.appRoute) {
        or
      })
    } else None
  }

  def optionalRoute(): Route = null

  private def getResourceActor(cls: Class[_ <: SkysailResource[_]]) = actorRefsMap get cls.getName getOrElse {
    log info s"creating new actor for ${cls.getName}"
    val c = system.actorOf(Props.apply(cls), cls.getName)
    actorRefsMap += cls.getName -> c
    //system watch c
    c
  }

}


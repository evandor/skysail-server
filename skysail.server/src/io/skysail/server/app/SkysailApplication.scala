package io.skysail.server.app

import java.util.concurrent.atomic.AtomicInteger
import java.util.{Collections, ResourceBundle}

import akka.actor.{ActorRef, ActorSelection, ActorSystem, Props}
import akka.http.scaladsl.server.{Route, _}
import io.skysail.domain.Resource
import io.skysail.domain.app.{ApiVersion, ApplicationApi}
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.Constants
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object SkysailApplication {
  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  case class InitResourceActorChain(val requestContext: RequestContext, val cls: Class[_ <: Resource[_]])

  case class CreateApplicationActor(val cls: Class[_ <: SkysailApplication], val appModel: ApplicationModel, val application: ApplicationApi, bundleContext: BundleContext)

  case class DeleteApplicationActor(val cls: Class[_ <: SkysailApplication])

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

  //  def getBundleActor(system: ActorSystem, symbolicName: String): Option[ActorSelection] = {
  //
  //    val bundlesActor = getBundlesActor(system)
  //
  //    bundlesActor ? BundlesActor.GetBundleBySymbolicName
  //
  //    val actorSelection = "/user/" + Constants.BUNDLES_ACTOR_NAME + "/" + bundleId.toString
  //    println("searching for actorSelection " + actorSelection)
  //    system.actorSelection(actorSelection)
  //  }
}

abstract class SkysailApplication(
                                   name: String,
                                   val apiVersion: ApiVersion,
                                   val bundleContext: BundleContext,
                                   description: String) extends ApplicationProvider with ApplicationApi {

  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  def this(name: String, bundleContext: BundleContext, description: String) =
    this(name, new ApiVersion(1), bundleContext, description)

  val appModel = new ApplicationModel(name, apiVersion, description)

  def routesMappings: List[RouteMapping[_]]

  var actorRefsMap = Map.empty[String, ActorRef]

  val routes = {
    routesMappings.foreach(m => {
      appModel.addResourceModel(m)
    })
    routesMappings // ++ List(RouteMapping("/_model", classOf[ModelResource]))
  }

  def application(): SkysailApplication = this

  //val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: SkysailServerResource[_]]]]()

  //var componentContext: ComponentContext = null
  //def getComponentContext() = componentContext

  var host = "localhost"

  def getHost = host

  var appRoutes: List[Route] = _
  var system: ActorSystem = _
  val cnt = new AtomicInteger(0)

  //val stringContextMap = new java.util.HashMap[ApplicationContextId, String]()

  def getResourceBundles() = List[ResourceBundle]()

  def getTemplatePaths[T](x$1: Class[T]): java.util.List[String] = {
    Collections.emptyList()
  }

  //  @Activate
  //  def activate(componentContext: ComponentContext) = {
  //    log info s"activating ${this.getClass.getName}"
  //    this.componentContext = componentContext;
  //  }
  //
  //  @Activate
  //  def activate(appConfig: ApplicationConfiguration, componentContext: ComponentContext): Unit = {
  //    activate(componentContext);
  //    host = appConfig.host();
  //  }
  //
  //  @Deactivate
  //  def deactivate(componentContext: ComponentContext): Unit = {
  //    log info s"deactivating ${this.getClass.getName}"
  //    this.componentContext = null;
  //  }

  def getSkysailApplication() = this

  //  def getBundle(): Bundle = {
  //    if (componentContext == null) {
  //      return null;
  //    }
  //    return componentContext.getBundleContext().getBundle();
  //  }

  private def getResourceActor(cls: Class[_ <: Resource[_]]) = actorRefsMap get cls.getName getOrElse {
    log info s"creating new actor for ${cls.getName}"
    val c = system.actorOf(Props.apply(cls), cls.getName)
    actorRefsMap += cls.getName -> c
    //system watch c
    c
  }

}


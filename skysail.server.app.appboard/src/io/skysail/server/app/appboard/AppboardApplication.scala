package io.skysail.server.app.appboard

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ ApplicationProvider, BackendApplication }
import org.osgi.framework.BundleContext
import akka.actor.ActorRef
import io.skysail.server.Constants
import scala.concurrent.duration.DurationInt
import scala.concurrent.Await

object AppboardApplication {
  def getApplicationsActor(system: ActorSystem): ActorRef = {
    val applicationsActorPath = "/user/" + Constants.APPLICATIONS_ACTOR_NAME
    val applicationsActorSelection = system.actorSelection(applicationsActorPath)
    val r = applicationsActorSelection.resolveOne(2.seconds)
    Await.result(r, 1.seconds)
  }
}

class AppboardApplication(bundleContext: BundleContext, routesCreator: RoutesCreatorTrait, system: ActorSystem)
  extends BackendApplication(bundleContext, routesCreator, system)
  with ApplicationProvider {

  override def name = "appboard"

  override def desc = "Skysail Application Dashboard"

  val appService = new ApplicationService()

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(name) / PathMatcher("v1")
    List(
      RouteMapping("", root ~ PathEnd, classOf[AppsResource]))
  }

  //  def getBundles(): List[BundleDescriptor] = {
  //    val bundles = bundleContext.getBundles().toList
  //    bundles.map(BundleDescriptor(_))
  //  }

}
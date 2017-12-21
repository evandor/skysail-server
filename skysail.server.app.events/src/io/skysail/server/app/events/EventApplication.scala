package io.skysail.server.app.events

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.BundleContext

class EventApplication(bundleContext: BundleContext, routesCreator: RoutesCreatorTrait, system: ActorSystem, eventsService: EventsService) extends
  BackendApplication(bundleContext, routesCreator, system) with ApplicationProvider {

  override def name = "events"

  override def desc = "Skysail Bundle Repository Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(name) / PathMatcher("v1")
    List(
      RouteMapping("/test", root / PathMatcher("test") ~ PathEnd, classOf[EventsResource])
    )
  }

}
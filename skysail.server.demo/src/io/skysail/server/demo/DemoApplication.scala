package io.skysail.server.demo

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.api.persistence.DbService
import io.skysail.domain.routes._
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import io.skysail.server.demo.repositories.{BookmarksRepository, DbConfigsRepository}
import io.skysail.server.demo.resources._
import io.skysail.server.demo.services.{BookmarksService, EventService}
import org.osgi.framework.BundleContext
import org.osgi.service.event.EventAdmin

class DemoApplication(bundleContext: BundleContext, dbService: DbService, system: ActorSystem, routesCreator: RoutesCreatorTrait) extends
  BackendApplication(bundleContext, routesCreator,system) with ApplicationProvider {

  var eventService: EventService = new EventService(null)

  def setEventAdmin(eventAdmin: EventAdmin) = {
    eventService = new EventService(eventAdmin)
  }

  val repo = new BookmarksRepository(dbService)

  val dbConfigRepo = new DbConfigsRepository(dbService)

  val bookmarksService = new BookmarksService()

  override def name = "demo"
  override def desc = "Skysail Demo Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("/bms",        root / PathMatcher("bms") ~ PathEnd, classOf[BookmarksResource]),
      RouteMapping("/bms/",       root / PathMatcher("bms") / PathEnd, classOf[PostBookmarkResource]),
      RouteMapping("/bms/:id",    root / PathMatcher("bms") / Segment ~ PathEnd, classOf[BookmarkResource]),
      RouteMapping("/bms/:id/",   root / PathMatcher("bms") / Segment / PathEnd, classOf[PutBookmarkResource]),

      ListRouteMapping("/dbconfigs",      root / PathMatcher("dbconfigs") ~ PathEnd, classOf[DbConfigsResource]),
      CreationMapping("/dbconfigs/",     root / PathMatcher("dbconfigs") / PathEnd, classOf[DbConfigsResource]),
      EntityMapping("/dbconfigs/:id",  root / PathMatcher("dbconfigs") / Segment ~ PathEnd, classOf[DbConfigsResource]),
      UpdateMapping("/dbconfigs/:id/", root / PathMatcher("dbconfigs") / Segment / PathEnd, classOf[DbConfigsResource]),

      RouteMapping("/es/indices", root / PathMatcher("es") / PathMatcher("indices") ~ PathEnd, classOf[IndicesResource])
    )
  }

}
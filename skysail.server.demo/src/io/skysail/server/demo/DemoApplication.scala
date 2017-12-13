package io.skysail.server.demo

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.api.persistence.DbService
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import io.skysail.server.demo.repositories.BookmarksRepository
import io.skysail.server.demo.resources._
import io.skysail.server.demo.services.BookmarksService
import org.osgi.framework.BundleContext

class DemoApplication(bundleContext: BundleContext, dbService: DbService, system: ActorSystem) extends
  BackendApplication(bundleContext, system) with ApplicationProvider {

  val repo = new BookmarksRepository(dbService)

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

      RouteMapping("/es/indices", root / PathMatcher("es") / PathMatcher("indices") ~ PathEnd, classOf[IndicesResource])
    )
  }

}
package io.skysail.server.demo

import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.api.persistence.DbService
import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.{ApplicationProvider, SkysailApplication}
import io.skysail.server.demo.DemoApplication._
import io.skysail.server.demo.repositories.BookmarksRepository
import io.skysail.server.demo.resources.{BookmarksResource, IndicesResource, PostBookmarkResource, PutBookmarkResource}
import org.osgi.framework.BundleContext

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val APP_VERSION = ApiVersion(1)
}

class DemoApplication(bundleContext: BundleContext, dbService: DbService) extends
  SkysailApplication(APPLICATION_NAME, APP_VERSION, bundleContext, "Skysail Demo Application") with ApplicationProvider {

  val repo = new BookmarksRepository(dbService)

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("/bms",        root / PathMatcher("bms") ~ PathEnd, classOf[BookmarksResource]),
      RouteMapping("/bms/",       root / PathMatcher("bms") / PathEnd, classOf[PostBookmarkResource]),
      RouteMapping("/bms/:id/",   root / PathMatcher("bms") / Segment / PathEnd, classOf[PutBookmarkResource]),

      RouteMapping("/es/indices", root / PathMatcher("es") / PathMatcher("indices") ~ PathEnd, classOf[IndicesResource])
    )
  }


}
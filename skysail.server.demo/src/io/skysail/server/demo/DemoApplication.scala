package io.skysail.server.demo

import akka.http.scaladsl.server.{PathMatcher, PathMatchers}
import io.skysail.api.persistence.DbService
import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.{ApplicationProvider, SkysailApplication}
import io.skysail.server.demo.DemoApplication._
import io.skysail.server.demo.repositories.BookmarksRepository
import io.skysail.server.demo.resources.{BookmarksResource, PostBookmarkResource}
import org.osgi.framework.BundleContext

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val APP_VERSION = ApiVersion(1)
}

class DemoApplication(bundleContext: BundleContext, dbService: DbService) extends
  SkysailApplication(APPLICATION_NAME, APP_VERSION, bundleContext, "Skysail Doc Application") with ApplicationProvider {


  //  @Activate
  //  override def activate(appConfig: ApplicationConfiguration, componentContext: ComponentContext): Unit = {
  //    super.activate(appConfig, componentContext)
  //    println("NEW DemoRepository")
  val repo = new BookmarksRepository(dbService)
  //  }
  //
  //  @Deactivate
  //  override def deactivate(componentContext: ComponentContext): Unit = {
  //    super.deactivate(componentContext)
  //    repo = null
  //  }

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("bm", root / PathMatcher("bm") ~ PathMatchers.PathEnd, classOf[BookmarksResource]),
      RouteMapping("bm/", root / PathMatcher("bm") ~ PathMatchers.Slash ~ PathMatchers.PathEnd, classOf[PostBookmarkResource]),
      RouteMapping("dev", root / PathMatcher("dev") ~ PathMatchers.PathEnd, classOf[DevDocResource]),
      RouteMapping("history", root / PathMatcher("history") ~ PathMatchers.PathEnd, classOf[HistoryDocResource]),
      RouteMapping("meta", root / PathMatcher("meta") ~ PathMatchers.PathEnd, classOf[MetaDocResource])
    )
  }


}
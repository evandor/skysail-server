package io.skysail.server.demo

import akka.http.scaladsl.server.{PathMatcher, PathMatchers}
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.{ApplicationProvider, SkysailApplication}
import io.skysail.server.demo.DemoApplication._
import org.osgi.framework.BundleContext
import io.skysail.domain.app.ApiVersion

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val APP_VERSION = ApiVersion(1)
}

class DemoApplication(bundleContext: BundleContext) extends
  SkysailApplication(APPLICATION_NAME,APP_VERSION, bundleContext, "Skysail Doc Application") with ApplicationProvider {


  //  @Activate
  //  override def activate(appConfig: ApplicationConfiguration, componentContext: ComponentContext): Unit = {
  //    super.activate(appConfig, componentContext)
  //    println("NEW DemoRepository")
  //    repo = new DemoRepository(dbService)
  //  }
  //
  //  @Deactivate
  //  override def deactivate(componentContext: ComponentContext): Unit = {
  //    super.deactivate(componentContext)
  //    repo = null
  //  }

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("doc")
    List(
      RouteMapping("_info", root / PathMatcher("_info") ~ PathMatchers.PathEnd, classOf[InfoResource]),
      RouteMapping("dev",   root / PathMatcher("dev")   ~ PathMatchers.PathEnd, classOf[DevDocResource]),
      RouteMapping("history",   root / PathMatcher("history")   ~ PathMatchers.PathEnd, classOf[HistoryDocResource]),
      RouteMapping("meta",  root / PathMatcher("meta")  ~ PathMatchers.PathEnd, classOf[MetaDocResource])
    )
  }


}
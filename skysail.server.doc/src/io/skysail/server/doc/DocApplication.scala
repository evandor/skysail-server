package io.skysail.server.doc

import akka.http.scaladsl.server.PathMatcher
import io.skysail.domain.ApplicationProvider
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.SkysailApplication
import io.skysail.server.doc.DocApplication._

object DocApplication {
  val APPLICATION_NAME = "doc"
}

class DocApplication extends SkysailApplication(APPLICATION_NAME, "Skysail Doc Application") with ApplicationProvider {


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
    val root: PathMatcher[Unit] = PathMatcher("root")
    List(
      RouteMapping("", root, classOf[MetaDocResource]),
      RouteMapping("_info", root / "_info", classOf[DocInfoResource])
    )
  }


}
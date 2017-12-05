package io.skysail.server.website

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{get, pathPrefix, _}
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.server.app.{ApplicationProvider, SkysailApplication}
import io.skysail.server.website.WebsiteApplication._
import org.osgi.framework.BundleContext

import scala.concurrent.ExecutionContextExecutor

object WebsiteApplication {
  val APPLICATION_NAME = "website"
}

class WebsiteApplication(bundleContext: BundleContext, system: ActorSystem) extends
  SkysailApplication(APPLICATION_NAME, bundleContext, "Skysail Server Website Application")
  with ApplicationProvider {


  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(APPLICATION_NAME)
    List(
      //      RouteMapping("_info", root / PathMatcher("_info") ~ PathMatchers.PathEnd, classOf[DocInfoResource]),
      //      RouteMapping("dev", root / PathMatcher("dev") ~ PathMatchers.PathEnd, classOf[DevDocResource]),
      //      RouteMapping("history", root / PathMatcher("history") ~ PathMatchers.PathEnd, classOf[HistoryDocResource]),
      //      RouteMapping("meta", root / PathMatcher("meta") ~ PathMatchers.PathEnd, classOf[MetaDocResource]),
      //RouteMapping("index.html", root / PathMatcher("index.html") ~ PathMatchers.PathEnd, classOf[DocIndexResource])
    )
  }

  override def optionalRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("") {
      get {
        getFromResourceDirectory("assets/webapp", this.getClass.getClassLoader)
      }
    }
  }


}

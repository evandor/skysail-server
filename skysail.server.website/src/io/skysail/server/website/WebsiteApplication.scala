package io.skysail.server.website

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{get, pathPrefix, _}
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.BundleContext

import scala.concurrent.ExecutionContextExecutor

class WebsiteApplication(bundleContext: BundleContext, system: ActorSystem) extends
  BackendApplication(bundleContext, null, null)
  with ApplicationProvider {

  override def name = "website"
  override def desc = "Skysail Server Website Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(name)
    List(
      //      RouteMapping("_info", root / PathMatcher("_info") ~ PathMatchers.PathEnd, classOf[DocInfoResource]),
      //      RouteMapping("dev", root / PathMatcher("dev") ~ PathMatchers.PathEnd, classOf[DevDocResource]),
      //      RouteMapping("history", root / PathMatcher("history") ~ PathMatchers.PathEnd, classOf[HistoryDocResource]),
      //      RouteMapping("meta", root / PathMatcher("meta") ~ PathMatchers.PathEnd, classOf[MetaDocResource]),
      //RouteMapping("index.html", root / PathMatcher("index.html") ~ PathMatchers.PathEnd, classOf[DocIndexResource])
    )
  }

  override def nativeRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("") {
      get {
        getFromResourceDirectory("assets/webapp", this.getClass.getClassLoader)
      }
    }
  }

}

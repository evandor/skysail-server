package io.skysail.server.doc

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{get, pathPrefix, _}
import akka.http.scaladsl.server.{PathMatcher, PathMatchers, Route}
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.{ApplicationProvider, SkysailApplication}
import io.skysail.server.doc.DocApplication._
import org.osgi.framework.BundleContext

import scala.concurrent.ExecutionContextExecutor

object DocApplication {
  val APPLICATION_NAME = "doc"
}

// tag::body[]
class DocApplication(bundleContext: BundleContext, system: ActorSystem) extends
  SkysailApplication(APPLICATION_NAME, bundleContext, "Skysail Doc Application")
  with ApplicationProvider {


  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("doc")
    List(
      RouteMapping("_info", root / PathMatcher("_info") ~ PathMatchers.PathEnd, classOf[DocInfoResource]),
      RouteMapping("dev", root / PathMatcher("dev") ~ PathMatchers.PathEnd, classOf[DevDocResource]),
      RouteMapping("history", root / PathMatcher("history") ~ PathMatchers.PathEnd, classOf[HistoryDocResource]),
      RouteMapping("meta", root / PathMatcher("meta") ~ PathMatchers.PathEnd, classOf[MetaDocResource]),
      RouteMapping("index.html", root / PathMatcher("index.html") ~ PathMatchers.PathEnd, classOf[DocIndexResource])
    )
  }

  override def optionalRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("xxx") {
      get {
        //complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        //getFromResource("assets/html5/meta.html", ContentTypes.`text/html(UTF-8)`, this.getClass.getClassLoader)
        getFromResourceDirectory("assets/html5", this.getClass.getClassLoader)
      }
    }
  }


}
// end::body[]

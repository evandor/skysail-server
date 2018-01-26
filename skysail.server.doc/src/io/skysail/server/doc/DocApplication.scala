package io.skysail.server.doc

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{get, pathPrefix, _}
import akka.http.scaladsl.server.{PathMatcher, PathMatchers, Route}
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.BundleContext

import scala.concurrent.ExecutionContextExecutor

// tag::body[]
class DocApplication(bundleContext: BundleContext, routesCreator: RoutesCreatorTrait, system: ActorSystem) extends
  BackendApplication(bundleContext, routesCreator, system)
  with ApplicationProvider {

  override def name = "doc"

  override def desc = "Skysail Doc Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("doc") / "v1"
    List(
      RouteMapping("", root ~ PathMatchers.PathEnd, classOf[DocIndexResource]),

      RouteMapping("index.html", root / PathMatcher("index.html") ~ PathMatchers.PathEnd, classOf[DocIndexResource])
    )
  }

  override def nativeRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("") {
      get {
        getFromResourceDirectory("assets/html5", this.getClass.getClassLoader)
      }
    }
  }

}

// end::body[]

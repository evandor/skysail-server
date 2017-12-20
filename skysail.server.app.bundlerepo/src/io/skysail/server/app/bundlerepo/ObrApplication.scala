package io.skysail.server.app.bundlerepo

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.api.osgi.bundlerepository.RepositoryService
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import io.skysail.server.app.bundlerepo.resources.{ReposResource, ResourcesResource}
import org.osgi.framework.BundleContext

class ObrApplication(
                      bc: BundleContext,
                      rc: RoutesCreatorTrait,
                      sys: ActorSystem,
                      val repoService: RepositoryService) extends BackendApplication(bc, rc, sys)
  with ApplicationProvider {

  override def name = "obr"

  override def desc = "Skysail Bundle Repository Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(name) / PathMatcher("v1")
    List(
      RouteMapping("/repos", root / PathMatcher("repos") ~ PathEnd, classOf[ReposResource]),
      RouteMapping("/resources", root / PathMatcher("resources") ~ PathEnd, classOf[ResourcesResource])
    )
  }

}
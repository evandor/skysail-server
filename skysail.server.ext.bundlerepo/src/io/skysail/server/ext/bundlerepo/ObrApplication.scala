package io.skysail.server.ext.bundlerepo

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.apache.felix.bundlerepository.RepositoryAdmin
import org.osgi.framework.BundleContext

class ObrApplication(bundleContext: BundleContext, routesCreator: RoutesCreatorTrait, system: ActorSystem, repoAdmin: RepositoryAdmin) extends
  BackendApplication(bundleContext, routesCreator, system) with ApplicationProvider {

  override def name = "obr"

  override def desc = "Skysail Bundle Repository Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("/bms", root / PathMatcher("bms") ~ PathEnd, classOf[ReposResource])
    )
  }

}
package io.skysail.server.app.osgi

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.osgi.domain.BundleDescriptor
import io.skysail.server.app.osgi.resources.BundlesResource
import io.skysail.server.app.{ ApplicationProvider, BackendApplication }
import org.osgi.framework.BundleContext

class OsgiApplication(bundleContext: BundleContext, routesCreator: RoutesCreatorTrait, system: ActorSystem) extends BackendApplication(bundleContext, routesCreator, system) with ApplicationProvider {

  override def name = "osgi"

  override def desc = "Skysail OSGi Introspection Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(name) / PathMatcher("v1")
    List(
      RouteMapping("", root ~ PathEnd, classOf[BundlesResource]),
      RouteMapping("/bundles", root / PathMatcher("bundles") ~ PathEnd, classOf[BundlesResource])
    )
  }

  def getBundles(): List[BundleDescriptor] = {
    val bundles = bundleContext.getBundles().toList
    bundles.map(BundleDescriptor(_))
  }

}
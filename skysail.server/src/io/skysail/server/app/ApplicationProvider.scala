package io.skysail.server.app

import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.RouteMapping
import org.osgi.framework.BundleContext

trait ApplicationProvider {
  def appModel(): ApplicationModel
  def routes(): List[RouteMapping[_]]
  val bundleContext: BundleContext
  def application(): SkysailApplication
  //def menu(): Option[MenuItem] = None
}
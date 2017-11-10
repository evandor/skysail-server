package io.skysail.domain

import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.RouteMapping
import org.osgi.framework.BundleContext

trait ApplicationProvider {
  def appModel(): ApplicationModel
  def routes(): List[RouteMapping[_]]
  def getBundleContext(): Option[BundleContext] 
  //def application(): SkysailApplication
  //def menu(): Option[MenuItem] = None
}
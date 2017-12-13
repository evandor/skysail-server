package io.skysail.server.app

import akka.http.scaladsl.server.Route
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.model.ApplicationModel
import org.osgi.framework.BundleContext

trait ApplicationProvider {
  def appModel(): ApplicationModel
  //def routes(): List[RouteMapping[_,_]]
  def router: Route
  val bundleContext: BundleContext
  def application(): ApplicationApi
  //def menu(): Option[MenuItem] = None
  def nativeRoute(): Option[Route]
}
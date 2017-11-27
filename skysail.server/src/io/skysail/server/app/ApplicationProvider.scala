package io.skysail.server.app

import akka.http.scaladsl.server.Route
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.RouteMapping
import org.osgi.framework.BundleContext

trait ApplicationProvider {
  def appModel(): ApplicationModel
  def routes(): List[RouteMapping[_,_]]
  val bundleContext: BundleContext
  def application(): ApplicationApi
  //def menu(): Option[MenuItem] = None
  def route(): Option[Route]
}
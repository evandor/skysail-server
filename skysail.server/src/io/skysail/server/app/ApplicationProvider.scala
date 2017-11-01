package io.skysail.server.app

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.PathMatcher
import io.skysail.domain.model.ApplicationModel
import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext
import io.skysail.domain.app.SkysailApplication
import io.skysail.domain.RouteMapping

trait ApplicationProvider {
  def appModel(): ApplicationModel
  def routes(): List[RouteMapping[_]]
  def getBundleContext(): Option[BundleContext] 
  def application(): SkysailApplication
  //def menu(): Option[MenuItem] = None
}
package io.skysail.server

import akka.http.scaladsl.server.Route
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.ApplicationProvider

trait RoutesCreatorTrait {
  def createRoute(mapping: RouteMapping[_, _], appProvider: ApplicationProvider): Route
}


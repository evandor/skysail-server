package io.skysail.server

import akka.http.scaladsl.server.Route
import io.skysail.domain.routes.RouteMappingI
import io.skysail.server.app.ApplicationProvider

trait RoutesCreatorTrait {
  def createRoute(mapping: RouteMappingI[_, _], appProvider: ApplicationProvider): Route
}


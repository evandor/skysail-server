package io.skysail.server.doc

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.ApplicationProvider

// tag::body[]
class DocActivator extends DominoActivator {

  whenBundleActive {
    whenServicesPresent[ActorSystem,RoutesCreatorTrait] { (system, routesCreator) =>
      val app = new DocApplication(bundleContext, routesCreator, system)
      app.providesService[ApplicationProvider]
    }
  }
}
// end::body[]

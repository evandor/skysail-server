package io.skysail.server.website

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.server.app.ApplicationProvider

class Activator extends DominoActivator {

  whenBundleActive {
    whenServicePresent[ActorSystem] { system =>
      val app = new WebsiteApplication(bundleContext, system)
      app.providesService[ApplicationProvider]
    }
  }
}

package io.skysail.server.website

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.server.app.ApplicationProvider
import org.slf4j.bridge.SLF4JBridgeHandler

class Activator extends DominoActivator {

  SLF4JBridgeHandler.removeHandlersForRootLogger
  SLF4JBridgeHandler.install

  whenBundleActive {
    whenServicePresent[ActorSystem] { system =>
      val app = new WebsiteApplication(bundleContext, system)
      app.providesService[ApplicationProvider]
    }
  }
}

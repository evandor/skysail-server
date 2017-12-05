package io.skysail.server.doc

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.server.app.ApplicationProvider

// tag::body[]
class DocActivator extends DominoActivator {

  whenBundleActive {
    whenServicePresent[ActorSystem] { system =>
      val app = new DocApplication(bundleContext, system)
      app.providesService[ApplicationProvider]
    }
  }
}
// end::body[]

package io.skysail.server.demo

import domino.DominoActivator
import io.skysail.server.app.ApplicationProvider

class DemoActivator extends DominoActivator {

  whenBundleActive {
    //whenServicePresent[DbService] { s =>
      val app = new DemoApplication(bundleContext)
      //app.dbService = s
      app.providesService[ApplicationProvider]
    //}
  }
}

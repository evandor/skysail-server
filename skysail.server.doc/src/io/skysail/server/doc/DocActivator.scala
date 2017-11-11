package io.skysail.server.doc

import domino.DominoActivator
import io.skysail.server.app.ApplicationProvider

class DocActivator extends DominoActivator {

  whenBundleActive {
    //whenServicePresent[DbService] { s =>
      val app = new DocApplication(bundleContext)
      //app.dbService = s
      app.providesService[ApplicationProvider]
    //}
  }
}

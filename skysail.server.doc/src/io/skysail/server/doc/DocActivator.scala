package io.skysail.server.doc

import domino.DominoActivator
import io.skysail.domain.ApplicationProvider

class DocActivator extends DominoActivator {

  whenBundleActive {
    //whenServicePresent[DbService] { s =>
      val app = new DocApplication()
      //app.dbService = s
      app.providesService[ApplicationProvider]
    //}
  }
}

package semanticui

import domino.DominoActivator
import org.slf4j.LoggerFactory

class SemanticUiActivator  extends DominoActivator{

  private var log = LoggerFactory.getLogger(this.getClass)

  whenBundleActive {

    onStart {
      log info s"activating ${this.getClass.getName}"
    }

    onStop {
      log info s"deactivating ${this.getClass.getName}"
    }

//    whenServicePresent[DbService] { dbService =>
//      log info s"dbService available in ${this.getClass.getName}"
//      app = new DemoApplication(bundleContext, dbService)
//      //app.activate()
//      app.providesService[ApplicationProvider]
//    }


  }
}

package semanticui

import domino.DominoActivator
import io.skysail.api.ui.Client
import org.slf4j.LoggerFactory

class SemanticUiActivator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  private class SemanticUiClient extends Client {
    override def name(): String = "semanticui"
  }

  whenBundleActive {

    onStart {
      log info s"activating ${this.getClass.getName}"
    }

    onStop {
      log info s"deactivating ${this.getClass.getName}"
    }

    new SemanticUiClient().providesService[Client]

  }
}

package io.skysail.server.demo

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.api.persistence.DbService
import org.slf4j.LoggerFactory
import io.skysail.server.app.ApplicationProvider

class DemoActivator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: DemoApplication = _

  whenBundleActive {

    onStart {
      log info s"activating ${this.getClass.getName}"
    }

    onStop {
      log info s"deactivating ${this.getClass.getName}"
      app = null
    }

    whenServicesPresent[DbService, ActorSystem] { (dbService, actorSystem) =>
      log info s"dbService available in ${this.getClass.getName}"
      app = new DemoApplication(bundleContext, dbService, actorSystem)
      //app.activate()
      app.providesService[ApplicationProvider]
    }

  }
}

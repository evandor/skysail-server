package io.skysail.server.demo

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.api.persistence.DbService
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.ApplicationProvider
import org.slf4j.LoggerFactory

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

    whenServicesPresent[DbService, RoutesCreatorTrait, ActorSystem] { (dbService, routesCreator, actorSystem) =>
      log info s"dbService available in ${this.getClass.getName}"
      app = new DemoApplication(bundleContext, dbService, actorSystem, routesCreator)
      //app.activate()
      app.providesService[ApplicationProvider]
    }

  }
}

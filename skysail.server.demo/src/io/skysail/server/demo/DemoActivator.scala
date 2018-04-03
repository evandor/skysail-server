package io.skysail.server.demo

import akka.actor.ActorSystem
import domino.DominoActivator
import domino.service_watching.ServiceWatcherEvent._
import io.skysail.api.persistence.DbService
import io.skysail.api.ui.MenuService
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.ApplicationProvider
import org.osgi.service.event.EventAdmin
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

      whenConfigurationActive("monitor") { conf: Map[String, Any] =>
        log info s"received configuration for 'monitor': $conf"
        if (conf != null) {
          app.setMonitorUrls(conf.values.map(_.toString))
        }
      }

    }

    watchServices[EventAdmin] {
      case AddingService(s, context) =>
        if (app != null) {
          app.setEventAdmin(s)
        }
      case ModifiedService(s, _) =>
      case RemovedService(s, _) =>
        if (app != null) {
          app.setEventAdmin(null)
        }
    }

    watchServices[MenuService] {
      case AddingService(s, context) =>
        if (app != null) {
          app.setMenuService(s)
        }
      case ModifiedService(s, _) =>
      case RemovedService(s, _) =>
        if (app != null) {
          app.setMenuService(null)
        }
    }
  }
}

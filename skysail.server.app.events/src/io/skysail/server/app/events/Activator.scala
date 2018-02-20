package io.skysail.server.app.events

import akka.actor.ActorSystem
import domino.DominoActivator
import org.slf4j.LoggerFactory
import io.skysail.server.app.ApplicationProvider
import io.skysail.server.RoutesCreatorTrait

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: EventApplication = _



  whenBundleActive {
    whenServicesPresent[EventsService, RoutesCreatorTrait, ActorSystem] { (eventsService, routesCreator, actorSystem) =>
      log info s"EventAdmin available in ${this.getClass.getName}, creating EventApplication"
//      val cmd = new SkysailObrCommands(repoAdmin)
//
//      cmd.providesService[Object](
//        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
//        "osgi.command.function" -> "listRepos")
//
//      cmd.providesService[Object](
//        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
//        "osgi.command.function" -> "search")

      app = new EventApplication(bundleContext, routesCreator, actorSystem, eventsService)
      app.providesService[ApplicationProvider]

    }

  }
}

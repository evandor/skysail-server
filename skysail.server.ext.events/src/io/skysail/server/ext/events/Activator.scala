package io.skysail.server.ext.events

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.server.app.ApplicationProvider
import io.skysail.server.{Constants, RoutesCreatorTrait}
import org.osgi.service.event.{EventAdmin, EventHandler}
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: EventApplication = _

  val eventHandler = new EventHandler() {
    override def handleEvent(event: org.osgi.service.event.Event): Unit = {
      log info s"$event"
    }
  }

  whenBundleActive {
    whenServicesPresent[EventAdmin, RoutesCreatorTrait, ActorSystem] { (repoAdmin, routesCreator, actorSystem) =>
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

      app = new EventApplication(bundleContext, routesCreator, actorSystem, repoAdmin)
      app.providesService[ApplicationProvider]

      eventHandler.providesService[EventHandler]
    }

  }
}

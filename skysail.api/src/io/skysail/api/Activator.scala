package io.skysail.api

import domino.DominoActivator
import domino.service_watching.ServiceWatcherEvent.{AddingService, ModifiedService, RemovedService}
import io.skysail.api.config.ConfigMover
import io.skysail.api.osgi.bundlerepository.RepositoryService
import io.skysail.api.osgi.bundlerepository.impl.{DefaultRepositoryService, NoOpRepositoryService, SkysailObrCommands}
import io.skysail.api.osgi.events.EventsService
import io.skysail.api.osgi.events.impl.DefaultEventsService
import org.apache.felix.bundlerepository.RepositoryAdmin
import org.osgi.service.event.{EventAdmin, EventHandler}
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private val log = LoggerFactory.getLogger(this.getClass())

  whenBundleActive {

    log info s"skysail api bundle became active"

    //new ConfigMover(bundleContext)

    watchServices[RepositoryAdmin] {

      case AddingService(service, context) => {
        log info s"RepositoryAdmin service became available, providing DefaultRepositoryService now"
        val repoService: RepositoryService = new DefaultRepositoryService(service);
        repoService.providesService[RepositoryService]

        log info s"RepositoryAdmin service became available, providing SkysailObrCommands now"
        val commands = new SkysailObrCommands(repoService)
        commands.providesService[Object]("osgi.command.scope" -> "skysail", "osgi.command.function" -> "listRepos")
        commands.providesService[Object]("osgi.command.scope" -> "skysail", "osgi.command.function" -> "search")
      }

      case ModifiedService(service, context) =>

      case RemovedService(service, _) => {
        log info s"RepositoryAdmin became unavailable, providing NoOpRepositoryService now"
        val repoService: RepositoryService = new NoOpRepositoryService();
        repoService.providesService[RepositoryService]
      }
    }

    watchServices[EventAdmin] {
      case AddingService(service, context) => {
        val eventsService: DefaultEventsService = new DefaultEventsService(service);
        eventsService.providesService[EventsService]
        eventsService.providesService[EventHandler]("EVENT_TOPIC" -> "*")
        //        new SkysailObrCommands(repoService).providesService[Object](
        //          "osgi.command.scope" -> "skysail",
        //          "osgi.command.function" -> "listRepos")
        eventsService.send("starting EventService")
      }
      case ModifiedService(service, context) =>
      case RemovedService(service, _) => {
        val repoService: RepositoryService = new NoOpRepositoryService();
        repoService.providesService[RepositoryService]
      }
    }

  }

}
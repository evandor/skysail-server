package io.skysail.server.app.bundlerepo

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.api.osgi.bundlerepository.RepositoryService
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.ApplicationProvider
import org.apache.felix.bundlerepository.RepositoryAdmin
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: ObrApplication = _

  whenBundleActive {
    //whenServicePresent[RepositoryAdmin] { repoAdmin =>
    whenServicesPresent[RepositoryService, RoutesCreatorTrait, ActorSystem] { (repoService, routesCreator, actorSystem) =>
//      log info s"RepositoryAdmin available in ${this.getClass.getName}, creating commands and SkysailObrApplication"
//      val cmd = new SkysailObrCommands(repoAdmin)
//
//      cmd.providesService[Object](
//        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
//        "osgi.command.function" -> "listRepos")
//
//      cmd.providesService[Object](
//        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
//        "osgi.command.function" -> "search")

      app = new ObrApplication(bundleContext, routesCreator, actorSystem, repoService)
      app.providesService[ApplicationProvider]
    }

  }
}

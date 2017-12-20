package io.skysail.server.seed

import domino.DominoActivator
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)


  whenBundleActive {
    //whenServicePresent[RepositoryAdmin] { repoAdmin =>
//    whenServicesPresent[RepositoryAdmin, RoutesCreatorTrait, ActorSystem] { (repoAdmin, routesCreator, actorSystem) =>
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
//
//      app = new ObrApplication(bundleContext, routesCreator, actorSystem, repoAdmin)
//      app.providesService[ApplicationProvider]
//    }

  }
}

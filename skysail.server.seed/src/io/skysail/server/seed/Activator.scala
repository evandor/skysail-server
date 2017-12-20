package io.skysail.server.seed

import domino.DominoActivator
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

class Activator extends DominoActivator {

  SLF4JBridgeHandler.removeHandlersForRootLogger
  SLF4JBridgeHandler.install

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

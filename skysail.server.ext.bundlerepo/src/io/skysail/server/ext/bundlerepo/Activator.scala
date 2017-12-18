package io.skysail.server.ext.bundlerepo

import domino.DominoActivator
import io.skysail.server.Constants
import org.apache.felix.bundlerepository.RepositoryAdmin
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  whenBundleActive {

    whenServicePresent[RepositoryAdmin] { repoAdmin =>
      log info s"RepositoryAdmin available in ${this.getClass.getName}"
      //app = new DemoApplication(bundleContext, dbService, actorSystem, routesCreator)
      //app.providesService[ApplicationProvider]

      val cmd = new ListReposCommand(repoAdmin)
      cmd.providesService[Object](
        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
        "osgi.command.function" -> "listRepos")
      cmd.providesService[Object](
        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
        "osgi.command.function" -> "search")
    }
  }
}

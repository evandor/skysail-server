package io.skysail.server.ext.bundlerepo

import domino.DominoActivator
import io.skysail.server.Constants
import io.skysail.server.app.ApplicationProvider
import org.apache.felix.bundlerepository.RepositoryAdmin
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: SkysailObrApplication = _

  whenBundleActive {

    whenServicePresent[RepositoryAdmin] { repoAdmin =>
      log info s"RepositoryAdmin available in ${this.getClass.getName}, creating commands and SkysailObrApplication"

      val cmd = new SkysailObrCommands(repoAdmin)

      cmd.providesService[Object](
        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
        "osgi.command.function" -> "listRepos")

      cmd.providesService[Object](
        "osgi.command.scope" -> Constants.SKYSAIL_COMMAND_SCOPE,
        "osgi.command.function" -> "search")

      app = new SkysailObrApplication(repoAdmin)
      app.providesService[ApplicationProvider]

    }

  }
}

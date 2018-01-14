package io.skysail.server.app.osgi

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.api.osgi.bundlerepository.RepositoryService
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.ApplicationProvider
import org.apache.felix.bundlerepository.RepositoryAdmin
import org.slf4j.LoggerFactory
import org.osgi.util.tracker.ServiceTracker

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  //var app: ObrApplication = _
  //var logServiceTracker: ServiceTracker[LogService,LogService] = _

  whenBundleActive {

    onStart {
      println("Bundle started")
      //      logServiceTracker = new ServiceTracker(context, classOf[LogService].getName(), null);
      //logServiceTracker.open();
      //this.bundleContext = context;
      //osgiService = context.registerService(OsgiService.class, this, new Hashtable<>());
      //LogServiceUtils.info(logServiceTracker, "started activator " + this.getClass().getName());

    }

    onStop {
      println("Bundle stopped")
      //LogServiceUtils.info(logServiceTracker, "about to stop activator " + this.getClass().getName());
      //  this.bundleContext = null;
      //  osgiService = null;
      // logServiceTracker.close();
      // logServiceTracker = null;
    }

    //whenServicePresent[RepositoryAdmin] { repoAdmin =>
    //whenServicesPresent[RepositoryService, RoutesCreatorTrait, ActorSystem] { (repoService, routesCreator, actorSystem) =>
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

      //app = new ObrApplication(bundleContext, routesCreator, actorSystem, repoService)
      //app.providesService[ApplicationProvider]
    //}

  }
}

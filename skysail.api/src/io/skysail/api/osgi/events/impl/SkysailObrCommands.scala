package io.skysail.api.osgi.events.impl

import org.apache.felix.bundlerepository.RepositoryAdmin
import io.skysail.api.osgi.bundlerepository.RepositoryService

class SkysailObrCommands(repoService: RepositoryService) {

  //private val app = new ObrApplication(null, null, null, repoAdmin)

  def listRepos(): Unit = {

    //val repos = new RepoService().repoAdmin.

//    repos
//      //.map(_.toString)
//      //.sorted
//      .foreach(key =>
//      //log.info(s"$key.name => '${key.uri}'"
//      )
//    )
  }

//  def search(searchFor: String): Unit = {
//    log.info("Search Repositories:")
//    log.info("====================")
//
//    val filter = "(|(presentationname=*)(symbolicname=*))"
//    val resources = repoAdmin.discoverResources(filter)
//    resources
//      //.map(_.toString)
//      //.sorted
//      .foreach(key =>
//      log.info(s"$key")// => '${key.getSymbolicName}'")
//    )
//
//  }
}
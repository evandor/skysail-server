package io.skysail.server.ext.bundlerepo

import org.apache.felix.bundlerepository.RepositoryAdmin
import org.slf4j.LoggerFactory

class SkysailObrCommands(repoAdmin: RepositoryAdmin) {

  private val log = LoggerFactory.getLogger(this.getClass)

  def listRepos(): Unit = {
    log.info("List Repositories:")
    log.info("==================")

    val repos = repoAdmin.listRepositories.to[Seq]

    repos
      //.map(_.toString)
      //.sorted
      .foreach(key =>
      log.info(s"$key => '${key.getURI}'")
    )
  }

  def search(searchFor: String): Unit = {
    log.info("Search Repositories:")
    log.info("====================")

    val filter = "(|(presentationname=*)(symbolicname=*))"
    val resources = repoAdmin.discoverResources(filter)
    resources
      //.map(_.toString)
      //.sorted
      .foreach(key =>
      log.info(s"$key => '${key.getSymbolicName}'")
    )

  }
}
package io.skysail.api.osgi.bundlerepository.impl

import org.apache.felix.bundlerepository.RepositoryAdmin
import io.skysail.api.osgi.bundlerepository.RepositoryService
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory

class SkysailObrCommands(repoService: RepositoryService) {

  private val log = LoggerFactory.getLogger(this.getClass())

  def listRepos(): Unit = {
    log.info("List Repositories:")
    log.info("==================")

    repoService.listRepos
      .foreach(key =>
        log.info(s"$key.name => '${key.uri}'"))
  }

  def search(searchFor: String): Unit = {
    log.info("Search Repositories:")
    log.info("====================")

    val filter = "(|(presentationname=*)(symbolicname=*))"
    val resources = repoService.list(filter)
    resources
      //.map(_.toString)
      //.sorted
      .foreach(key =>
        log.info(s"$key") // => '${key.getSymbolicName}'")
        )

  }
}
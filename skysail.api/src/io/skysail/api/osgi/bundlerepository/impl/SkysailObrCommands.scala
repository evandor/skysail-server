package io.skysail.api.osgi.bundlerepository.impl

import io.skysail.api.osgi.bundlerepository.RepositoryService
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

    val filter = s"(|(presentationname=*$searchFor*)(symbolicname=*$searchFor*))"
    val resources = repoService.list(filter)
    resources
      //.map(_.toString)
      //.sorted
      .foreach(key =>
      log.info(s"$key") // => '${key.getSymbolicName}'")
    )
  }

  def deploy(bundle: String): Unit = {
    log.info("Deploying...")
    log.info("============")

    repoService.deploy(bundle)


  }

}
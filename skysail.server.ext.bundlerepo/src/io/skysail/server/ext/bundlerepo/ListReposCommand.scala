package io.skysail.server.ext.bundlerepo

import java.util.Properties

import org.apache.felix.bundlerepository.RepositoryAdmin
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

class ListReposCommand(repoAdmin: RepositoryAdmin) {

  private val log = LoggerFactory.getLogger(this.getClass)

  def listRepos(): Unit = {
    log.info("List Repositories:")
    log.info("==================")

    val repos = repoAdmin.listRepositories.to[Seq]

    val properties: Properties = System.getProperties
    val propertyKeys: Seq[AnyRef] = properties.keys().asScala.toSeq

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

    val filter = "(id=skysail.server)"
    val resources = repoAdmin.discoverResources(searchFor)
    resources
      //.map(_.toString)
      //.sorted
      .foreach(key =>
      log.info(s"$key => '${key.getSymbolicName}'")
    )

  }
}
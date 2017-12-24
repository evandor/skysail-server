package io.skysail.api.osgi.bundlerepository.impl

import io.skysail.api.osgi.bundlerepository.RepositoryService
import io.skysail.api.osgi.bundlerepository.domain.{Repository, Resource}

class NoOpRepositoryService() extends RepositoryService {

  override def listRepos: List[Repository] = {
    List()
  }

  def list(filter: String): List[Resource] = {
    List()
  }

  override def deploy(bundle: String): Unit = {}
}

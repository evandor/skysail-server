package io.skysail.api.osgi.bundlerepository.impl

import io.skysail.api.osgi.bundlerepository.domain.Repository
import io.skysail.api.osgi.bundlerepository.domain.Resource
import org.apache.felix.bundlerepository.RepositoryAdmin
import io.skysail.api.osgi.bundlerepository.RepositoryService

class NoOpRepositoryService() extends RepositoryService {

  override def listRepos: List[Repository] = {
    List()
  }

  def list(filter: String): List[Resource] = {
    List()
  }
}

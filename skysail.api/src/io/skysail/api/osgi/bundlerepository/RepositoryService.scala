package io.skysail.api.osgi.bundlerepository

import io.skysail.api.osgi.bundlerepository.domain.Repository
import io.skysail.api.osgi.bundlerepository.domain.Resource

trait RepositoryService {
  def listRepos: List[Repository]
  def list(filter: String): List[Resource]
}
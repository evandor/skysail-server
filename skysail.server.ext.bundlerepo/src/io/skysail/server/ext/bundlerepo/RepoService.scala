package io.skysail.server.ext.bundlerepo

import io.skysail.server.ext.bundlerepo.domain.Repository
import org.apache.felix.bundlerepository.RepositoryAdmin

case class RepoService(repoAdmin: RepositoryAdmin) {
  def listRepos: List[Repository] = {
    repoAdmin.listRepositories().to[List].map(r => new Repository(r.getName, r.getURI))
  }

}

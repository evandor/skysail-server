package io.skysail.server.ext.bundlerepo

import io.skysail.server.ext.bundlerepo.domain.{Repository, Resource}
import org.apache.felix.bundlerepository.RepositoryAdmin

case class RepoService(repoAdmin: RepositoryAdmin) {

  def listRepos: List[Repository] = {
    repoAdmin.listRepositories().to[List].map(r => new Repository(r.getName, r.getURI))
  }

  def list: List[Resource] = {
    val filter = "(|(presentationname=*)(symbolicname=*))"
    repoAdmin.discoverResources(filter).to[List].map(r => Resource(r.getSymbolicName, r.getVersion))
  }
}

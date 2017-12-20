package io.skysail.api.osgi.bundlerepository.impl

import io.skysail.api.osgi.bundlerepository.domain.Repository
import io.skysail.api.osgi.bundlerepository.domain.Resource
import org.apache.felix.bundlerepository.RepositoryAdmin
import io.skysail.api.osgi.bundlerepository.RepositoryService

class DefaultRepositoryService(var repoAdmin: RepositoryAdmin) extends RepositoryService {

  override def listRepos: List[Repository] = {
    repoAdmin.listRepositories().to[List].map(r => new Repository(r.getName, r.getURI))
  }

  def list(filter: String): List[Resource] = {
    //val filter = "(|(presentationname=*)(symbolicname=*))"
    repoAdmin.discoverResources(filter).to[List].map(r => Resource(r.getSymbolicName, r.getVersion))
  }
}

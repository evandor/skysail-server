package io.skysail.api.osgi.bundlerepository.impl

import io.skysail.api.osgi.bundlerepository.domain.Repository
import io.skysail.api.osgi.bundlerepository.domain.Resource
import org.apache.felix.bundlerepository.RepositoryAdmin
import io.skysail.api.osgi.bundlerepository.RepositoryService
import org.apache.felix.bundlerepository

class DefaultRepositoryService(var repoAdmin: RepositoryAdmin) extends RepositoryService {

  override def listRepos: List[Repository] = {
    repoAdmin.listRepositories().to[List].map(r => new Repository(r.getName, r.getURI))
  }

  def list(filter: String): List[Resource] = {
    //val filter = "(|(presentationname=*)(symbolicname=*))"
    repoAdmin.discoverResources(filter).to[List].map(r => Resource(r.getSymbolicName, r.getVersion))
  }


  override def deploy(bundle: String): Unit = {
    val resolver = repoAdmin.resolver()
    val resource: Option[bundlerepository.Resource] = selectNewestVersion(searchRepository(bundle));

    if (resource.isDefined) {
      resolver.add(resource.get)
      if (resolver.resolve()) {
        val resources = resolver.getAddedResources();
        resolver.deploy(17)
        val added = resolver.getAddedResources()
      } else {
        val reqs = resolver.getUnsatisfiedRequirements()
        if (!reqs.isEmpty) {
        } else {
        }
      }
    }

  }

  private def selectNewestVersion(resources: List[bundlerepository.Resource]): Option[bundlerepository.Resource] = {
    if (!resources.isEmpty) Some(resources(0)) else None
  }

  private def searchRepository(bundle: String): List[bundlerepository.Resource] = {
    val filter = s"(|(presentationname=$bundle)(symbolicname=$bundle))"
    repoAdmin.discoverResources(filter).to[List]
  }


}

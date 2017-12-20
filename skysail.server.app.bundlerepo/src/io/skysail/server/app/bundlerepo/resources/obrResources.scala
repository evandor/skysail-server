package io.skysail.server.app.bundlerepo.resources

import io.skysail.api.osgi.bundlerepository.domain.{Repository, Resource}
import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource
import io.skysail.server.app.bundlerepo.ObrApplication

case class ReposResource() extends ListResource[ObrApplication, Repository] {

  override def getList(requestEvent: RequestEvent) = getApplication().repoService.listRepos

}

case class ResourcesResource() extends ListResource[ObrApplication, Resource] {

  override def getList(requestEvent: RequestEvent) = getApplication().repoService.list("")

}

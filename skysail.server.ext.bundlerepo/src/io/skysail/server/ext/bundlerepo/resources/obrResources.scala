package io.skysail.server.ext.bundlerepo.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource
import io.skysail.server.ext.bundlerepo.ObrApplication
import io.skysail.server.ext.bundlerepo.domain.{Repository, Resource}

case class ReposResource() extends ListResource[ObrApplication, Repository] {

  override def getList(requestEvent: RequestEvent) = getApplication().repoService.listRepos

}

case class ResourcesResource() extends ListResource[ObrApplication, Resource] {

  override def getList(requestEvent: RequestEvent) = getApplication().repoService.list

}

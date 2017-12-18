package io.skysail.server.ext.bundlerepo.resources

import io.skysail.domain.resources.ListResource
import io.skysail.server.ext.bundlerepo.ObrApplication
import io.skysail.server.ext.bundlerepo.domain.Repository

case class ReposResource() extends ListResource[ObrApplication, Repository] {

}

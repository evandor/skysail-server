package io.skysail.server.app.osgi.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource
import io.skysail.server.app.osgi.OsgiApplication
import io.skysail.server.app.osgi.domain.BundleDescriptor

class BundlesResource extends ListResource[OsgiApplication, BundleDescriptor] {

  override def getList(requestEvent: RequestEvent): List[BundleDescriptor] = {
    getApplication().getBundles()
  }
}

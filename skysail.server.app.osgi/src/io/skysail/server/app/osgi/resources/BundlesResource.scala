package io.skysail.server.app.osgi.resources

import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.osgi.OsgiApplication
import io.skysail.server.app.osgi.domain.BundleDescriptor

class BundlesResource extends EntityResource[OsgiApplication, BundleDescriptor] {

//  override def getList(requestEvent: RequestEvent): List[BundleDescriptor] = {
//    getApplication().getBundles()
//  }
  override def getEntity(re: RequestEvent): Option[BundleDescriptor] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
}

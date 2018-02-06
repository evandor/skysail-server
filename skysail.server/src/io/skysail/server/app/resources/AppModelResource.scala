package io.skysail.server.app.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.app.ApiVersion
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.{AppModelDescription, RootApplication}

class AppModelResource extends EntityResource[RootApplication,AppModelDescription]{
  override def getEntity(re: RequestEvent): Option[AppModelDescription] = {
    Some(AppModelDescription("name"))
  }
}

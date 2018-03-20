package io.skysail.server.app.resources

import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.server.app.{ResourceModelDescription, RootApplication}

class ResourceModelResource(model: ApplicationModel) extends EntityResource[RootApplication, ResourceModelDescription] {

  override def getEntity(re: RequestEvent): Option[ResourceModelDescription] = {
    val resourceId = re.cmd.urlParameter.head
    Some(ResourceModelDescription(model, resourceId))
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = { null }

}

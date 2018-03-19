package io.skysail.server.app.resources

import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.server.app.{EntityModelDescription, RootApplication}

class EntityModelResource(model: ApplicationModel) extends EntityResource[RootApplication, EntityModelDescription] {

  override def getEntity(re: RequestEvent): Option[EntityModelDescription] = {
    val entityId = re.cmd.urlParameter.head
    Some(EntityModelDescription(model, entityId))
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = { null }

}

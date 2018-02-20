package io.skysail.server.app.resources

import io.skysail.domain.{ RequestEvent, ResponseEventBase }
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.{ AppModelDescription, RootApplication }
import akka.actor.ActorSystem

class AppModelResource(model: ApplicationModel) extends EntityResource[RootApplication, AppModelDescription] {
  override def getEntity(re: RequestEvent): Option[AppModelDescription] = {
    Some(AppModelDescription(model))
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = { null }

}

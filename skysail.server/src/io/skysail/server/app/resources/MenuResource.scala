package io.skysail.server.app.resources

import io.skysail.api.ui.MenuItem
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.server.app.RootApplication

class MenuResource() extends EntityResource[RootApplication, MenuItem] {

  override def getEntity(requestEvent: RequestEvent): Option[MenuItem] = {
    val service = getApplication().menuService
    Some(service.find("").get)
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {null}


}
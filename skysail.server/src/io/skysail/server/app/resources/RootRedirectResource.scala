package io.skysail.server.app.resources

import io.skysail.domain.{RedirectResponseEvent, RequestEvent}
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.RootApplication

class RootRedirectResource() extends EntityResource {
  override def get(requestEvent: RequestEvent) = {
    val app = getApplication().asInstanceOf[RootApplication]
    val redirectTo = app.conf.get("landingPage.notAuthenticated")
    RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def getAsync(requestEvent: RequestEvent): Unit = ???
}

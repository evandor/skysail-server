package io.skysail.server.app.resources

import io.skysail.domain.{RedirectResponseEvent, RequestEvent}
import io.skysail.domain.resources.AsyncEntityResource
import io.skysail.server.app.RootApplication

class RootRedirectResource() extends AsyncEntityResource {
  override def get(requestEvent: RequestEvent): Unit = {
    val app = application.asInstanceOf[RootApplication]
    val redirectTo = app.conf.get("landingPage.notAuthenticated")
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
  }
}

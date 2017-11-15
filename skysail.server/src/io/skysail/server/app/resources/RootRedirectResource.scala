package io.skysail.server.app.resources

import io.skysail.domain.{RedirectResponseEvent, RequestEvent}
import io.skysail.domain.resources.AsyncEntityResource

class RootRedirectResource() extends AsyncEntityResource {
  override def get(requestEvent: RequestEvent): Unit = {

    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "")
  }
}

package io.skysail.server.demo

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncEntityResource
import io.skysail.server.app.resources.RootInfo

case class InfoResource() extends AsyncEntityResource {

  override def get(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! RootInfo("skysail demo","powered by skysail.")
  }

}

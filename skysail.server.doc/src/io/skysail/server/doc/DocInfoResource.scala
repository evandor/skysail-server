package io.skysail.server.doc

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncEntityResource
import io.skysail.server.app.resources.RootInfo

case class DocInfoResource() extends AsyncEntityResource {

  override def get(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! RootInfo("skysail doc!!!","powered by skysail.")
  }

}

package io.skysail.server.doc

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncStaticResource
import io.skysail.server.app.resources.RootInfo

case class MetaDocResource() extends AsyncStaticResource{

  override def get(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! RootInfo("skysail doc","powered by skysail.")

  }

}

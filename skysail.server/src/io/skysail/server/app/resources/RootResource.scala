package io.skysail.server.app.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncEntityResource
import org.json4s.JsonAST.JObject
import io.skysail.domain.ResponseEvent

class RootResource extends AsyncEntityResource[String] {

  override def get(requestEvent: RequestEvent) {
    requestEvent.controllerActor ! ResponseEvent(requestEvent, "hi")
  }


 
}
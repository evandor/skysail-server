package io.skysail.server.app.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncEntityResource
import org.json4s.JsonAST.JObject
import io.skysail.domain.ResponseEvent

case class RootInfo(title: String,description: String, info: String = "you are seeing this as no applications have been deployed yet.") 

class RootResource extends AsyncEntityResource[RootInfo] {

  override def get(requestEvent: RequestEvent) {
    requestEvent.controllerActor ! RootInfo("skysail server","powered by skysail.")
  }


 
}
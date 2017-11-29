package io.skysail.server.doc

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.EntityResource

case class DocInfoResource() extends EntityResource {

  override def get(requestEvent: RequestEvent) = {
     //RootInfo("skysail doc!!!","powered by skysail.")
    null
  }

  override def getAsync(requestEvent: RequestEvent): Unit = ???
}

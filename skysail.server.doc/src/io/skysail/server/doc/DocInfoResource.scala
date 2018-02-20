package io.skysail.server.doc

import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.resources.EntityResource

case class DocInfoResource() extends EntityResource {

  override def get(requestEvent: RequestEvent) = {
     //RootInfo("skysail doc!!!","powered by skysail.")
    null
  }

  override def getEntity(requestEvent: RequestEvent) = ???

  override def put(requestEvent: RequestEvent): ResponseEventBase = { null }

}

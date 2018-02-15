package io.skysail.server.app.resources

import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonProperty}
import io.skysail.api.ui.{Linkable, TextLink}
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.server.app.RootApplication

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
case class Client(name: String) extends Linkable {
  @JsonProperty("_links")
  override def _links = List(TextLink("self", s"clients/$name",""))
}

class ClientsResource() extends EntityResource[RootApplication, Client] {

  override def getEntity(requestEvent: RequestEvent) = {
//    val clients = getApplication().appService.clients
//    ResponseEvent(requestEvent, clients.map(c => Client(c.name)))
    ???
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {null}

}


package io.skysail.server.app.resources

import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonProperty}
import io.skysail.api.ui.{Link, Linkable}
import io.skysail.domain.resources.ListResource
import io.skysail.domain.{ListResponseEvent, RequestEvent}
import io.skysail.server.app.RootApplication

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
case class Client(name: String) extends Linkable {
  @JsonProperty("_links")
  override def _links = List(Link("self", s"clients/$name"))
}

class ClientsResource() extends ListResource[RootApplication, Client] {

  def get(requestEvent: RequestEvent) = {
    val clients = getApplication().appService.clients
    ListResponseEvent(requestEvent, clients.map(c => Client(c.name)))
  }


}


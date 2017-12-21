package io.skysail.server.app.events

import org.osgi.service.event.EventAdmin

case class EventsService(repoAdmin: EventAdmin) {

  def list: List[Event] = List(Event("hi"))

}

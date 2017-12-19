package io.skysail.server.ext.events

import org.osgi.service.event
import org.osgi.service.event.{EventAdmin, EventHandler}
import org.slf4j.LoggerFactory

case class EventsService(repoAdmin: EventAdmin) {

  def list: List[Event] = List(Event("hi"))

}

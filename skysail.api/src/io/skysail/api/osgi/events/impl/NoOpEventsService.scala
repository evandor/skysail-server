package io.skysail.api.osgi.events.impl

import io.skysail.api.osgi.events.EventsService

class NoOpEventsService() extends EventsService {
  override def send(msg: String): Unit = {}
}

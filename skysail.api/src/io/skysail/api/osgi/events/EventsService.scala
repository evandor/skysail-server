package io.skysail.api.osgi.events

trait EventsService {
  def send(msg: String): Unit
}
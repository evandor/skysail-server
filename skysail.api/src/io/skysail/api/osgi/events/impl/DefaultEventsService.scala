package io.skysail.api.osgi.events.impl

import io.skysail.api.osgi.events.EventsService
import org.osgi.service.event.EventAdmin

import scala.collection.JavaConverters._

class DefaultEventsService(eventAdmin: EventAdmin) extends EventsService {

  override def send(msg: String) = {
    //    val properties = new util.Hashtable[String, Any]
    //    properties.put("time", System.currentTimeMillis())
    //    properties.put("message", msg)
    var properties = scala.collection.mutable.Map[String, String]();
    properties += "message" -> msg
    val reportGeneratedEvent = new org.osgi.service.event.Event(
      "io/skysail/server/demo/services/GENERATED", properties.asJava)
    eventAdmin.sendEvent(reportGeneratedEvent)
  }
}

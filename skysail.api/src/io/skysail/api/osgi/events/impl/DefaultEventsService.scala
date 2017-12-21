package io.skysail.api.osgi.events.impl

import io.skysail.api.osgi.events.EventsService
import org.osgi.service.event.{Event, EventAdmin, EventHandler}
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

class DefaultEventsService(eventAdmin: EventAdmin) extends EventsService with EventHandler {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def send(msg: String) = {
    //    val properties = new util.Hashtable[String, Any]
    //    properties.put("time", System.currentTimeMillis())
    //    properties.put("message", msg)
    var properties = scala.collection.mutable.Map[String, Any]();
    properties += "message" -> msg
    properties += "time" -> System.currentTimeMillis()
    val reportGeneratedEvent = new org.osgi.service.event.Event(
      "io/skysail/server/demo/services/GENERATED", properties.asJava)
    eventAdmin.sendEvent(reportGeneratedEvent)
  }

  override def handleEvent(event: Event): Unit = {
    log info s"$event"
  }
}

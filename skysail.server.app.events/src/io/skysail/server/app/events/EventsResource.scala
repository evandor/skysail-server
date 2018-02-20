package io.skysail.server.app.events

import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.resources.EntityResource

class EventsResource extends EntityResource[EventApplication, Event] {

//  override def getList(requestEvent: RequestEvent): List[Event] = {
//    List()//getApplication().eventsService.list
//  }
  override def getEntity(re: RequestEvent): Option[Event] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  def put(requestEvent: RequestEvent): ResponseEventBase = {
    ???
  }
}

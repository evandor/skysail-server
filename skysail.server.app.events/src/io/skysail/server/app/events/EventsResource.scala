package io.skysail.server.app.events

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource

class EventsResource extends ListResource[EventApplication, Event] {

  override def getList(requestEvent: RequestEvent): List[Event] = {
    List()//getApplication().eventsService.list
  }

}

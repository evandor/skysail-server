package io.skysail.server.ext.events

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource

class EventsResource extends ListResource[EventApplication, Event] {

  override def getList(requestEvent: RequestEvent): List[Event] = {
    getApplication().repoService.list
  }

}

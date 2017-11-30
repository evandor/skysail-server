package io.skysail.server.app.resources

import io.skysail.api.ui.Client
import io.skysail.domain.resources.ListResource
import io.skysail.domain.{ListResponseEvent, RequestEvent}
import io.skysail.server.app.{ApplicationService, RootApplication}

class ClientsResource() extends ListResource[RootApplication, Client] {

  def get(requestEvent: RequestEvent) = {
    val appService: ApplicationService = getApplication().appService
    ListResponseEvent(requestEvent, appService.clients)
  }


}
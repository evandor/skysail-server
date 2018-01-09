package io.skysail.server.app.resources

import io.skysail.domain.{RedirectResponseEvent, RequestEvent}
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.RootApplication
import io.skysail.domain.resources.AsyncEntityResource

class RootRedirectResource() extends AsyncEntityResource {
  
  override def getEntityAsync(requestEvent: RequestEvent):Unit = {
    val app = getApplication().asInstanceOf[RootApplication]
    val redirectTo = app.conf.get("landingPage.notAuthenticated")
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
    
  }
  
  override def get(requestEvent: RequestEvent) = {
    val app = getApplication().asInstanceOf[RootApplication]
    val redirectTo = app.conf.get("landingPage.notAuthenticated")
    RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  //override def getAsync(requestEvent: RequestEvent): Unit = ???

  def getEntity(re: RequestEvent): Option[Nothing] = {
    ???
  }
}

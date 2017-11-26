package io.skysail.server.app.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.EntityResource

case class RootInfo(title: String, description: String, info: String = "you are seeing this as no applications have been deployed yet.")

class RootResource extends EntityResource[RootInfo] {

  override def get(requestEvent: RequestEvent) {
    val skysailServerBundle = bundleContext.getBundles.filter(_.getSymbolicName == "skysail.server").head
    requestEvent.controllerActor ! RootInfo("skysail server", "powered by skysail " + skysailServerBundle.getVersion)
  }


}
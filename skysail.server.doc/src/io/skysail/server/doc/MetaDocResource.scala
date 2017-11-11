package io.skysail.server.doc

import java.net.URL

import io.skysail.domain.resources.AsyncStaticResource
import io.skysail.domain.{HtmlResponseEvent, RequestEvent}

case class MetaDocResource() extends AsyncStaticResource {

  override def get(requestEvent: RequestEvent): Unit = {
    val url: URL = bundleContext.getBundle.getResource("assets/html5/meta.html")
    val is = url.openConnection().getInputStream()
    val content = scala.io.Source.fromInputStream(is).mkString
    requestEvent.controllerActor ! HtmlResponseEvent(requestEvent, content)
  }

}

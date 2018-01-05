package io.skysail.server.doc

import java.net.URL

import akka.actor.{ActorRef, ActorSystem}
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{AsyncStaticResource, EntityResource}
import io.skysail.domain.{AsyncResponseEvent, HtmlResponseEvent, RequestEvent, ResponseEvent}


class DocIndexResource() extends EntityResource {
  override def get(requestEvent: RequestEvent) = {
    ResponseEvent(requestEvent, "hi")
  }

  override def getAsync(requestEvent: RequestEvent): Unit = ???
}

abstract case class DocResource() extends AsyncStaticResource {

  override def get(requestEvent: RequestEvent) = {
    getAsync(requestEvent)
    AsyncResponseEvent(requestEvent)
  }

  protected def getHtml(requestEvent: RequestEvent, path: String) = {
    val url: URL = bundleContext.getBundle.getResource(path)
    val is = url.openConnection().getInputStream()
    val content = scala.io.Source.fromInputStream(is).mkString
    requestEvent.controllerActor ! HtmlResponseEvent(requestEvent, content)
  }
}

class MetaDocResource() extends DocResource {
  override def getAsync(requestEvent: RequestEvent): Unit = getHtml(requestEvent, "assets/html5/meta.html")
  override def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit = {
    get(RequestEvent(cmd, self))
  }

}

class DevDocResource() extends DocResource {
  override def getAsync(requestEvent: RequestEvent): Unit = getHtml(requestEvent, "assets/html5/developer.html")
  override def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit = {
    get(RequestEvent(cmd, self))
  }
}

class HistoryDocResource() extends DocResource {
  override def getAsync(requestEvent: RequestEvent): Unit = getHtml(requestEvent, "assets/html5/history.html")
  override def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit = {
    get(RequestEvent(cmd, self))
  }
}

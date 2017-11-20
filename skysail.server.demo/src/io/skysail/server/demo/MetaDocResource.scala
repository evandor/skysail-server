//package io.skysail.server.demo
//
//import java.net.URL
//
//import io.skysail.domain.resources.AsyncStaticResource
//import io.skysail.domain.{HtmlResponseEvent, RequestEvent}
//
//abstract case class DocResource() extends AsyncStaticResource {
//  protected def getHtml(requestEvent: RequestEvent, path: String) = {
//    val url: URL = bundleContext.getBundle.getResource(path)
//    val is = url.openConnection().getInputStream()
//    val content = scala.io.Source.fromInputStream(is).mkString
//    requestEvent.controllerActor ! HtmlResponseEvent(requestEvent, content)
//  }
//
//}
//
//class MetaDocResource() extends DocResource {
//  override def get(requestEvent: RequestEvent): Unit = getHtml(requestEvent, "assets/html5/meta.html")
//}
//
//class DevDocResource() extends DocResource {
//  override def get(requestEvent: RequestEvent): Unit = getHtml(requestEvent, "assets/html5/developer.html")
//}
//
//class HistoryDocResource() extends DocResource {
//  override def get(requestEvent: RequestEvent): Unit = getHtml(requestEvent, "assets/html5/history.html")
//}

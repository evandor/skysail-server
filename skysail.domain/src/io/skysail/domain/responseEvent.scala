package io.skysail.domain

import akka.http.scaladsl.model.HttpResponse
import io.skysail.domain.resources.AsyncResource

import scala.reflect.ClassTag

sealed trait ResponseEventBase {
  val req: RequestEvent
  val entity: Any
  val httpResponse: HttpResponse

  def getResource: Option[AsyncResource[_, _]] = req.cmd.resource
  
}

case class ResponseEvent[T](
                             req: RequestEvent,
                             entity: T,
                             entityManifest: Manifest[T] = null,
                             entityClassTag: ClassTag[T] = null,
                             httpResponse: HttpResponse = HttpResponse(200))
  extends ResponseEventBase

//case class ListResponseEvent[T](req: RequestEvent, entity: List[T], entityManifest: Manifest[T] = null, httpResponse: HttpResponse = HttpResponse(200))
//  extends ResponseEventBase {
//
//  override def toString: String = s"ListResponseEvent(${httpResponse})"
//
//}


case class HtmlResponseEvent(req: RequestEvent, entity: String, httpResponse: HttpResponse = HttpResponse(200))
  extends ResponseEventBase

case class RedirectResponseEvent(req: RequestEvent, entity: String, redirectTo: Option[Any], httpResponse: HttpResponse = HttpResponse(301))
  extends ResponseEventBase

case class AsyncResponseEvent(req: RequestEvent)
  extends ResponseEventBase {
  val entity: String = null
  val redirectTo: Option[Any] = None
  val httpResponse: HttpResponse = null
}
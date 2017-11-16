package io.skysail.domain

import akka.http.scaladsl.model.HttpResponse

sealed trait ResponseEventBase {
  val req: RequestEvent
  val entity: Any
  val httpResponse: HttpResponse
}

case class ResponseEvent[T](req: RequestEvent, entity: T, httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase

case class ListResponseEvent[T](req: RequestEvent, entity: T, httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase

case class HtmlResponseEvent(req: RequestEvent, entity: String, httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase

case class RedirectResponseEvent(req: RequestEvent, entity: String, redirectTo: Option[Any], httpResponse: HttpResponse = HttpResponse(301)) extends ResponseEventBase

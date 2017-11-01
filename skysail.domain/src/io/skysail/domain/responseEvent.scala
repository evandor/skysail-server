package io.skysail.domain

import akka.http.scaladsl.model.HttpResponse

sealed trait ResponseEventBase {
  val req: RequestEvent
  val entity: Any
  val httpResponse: HttpResponse
}

case class ResponseEvent[T](req: RequestEvent, val entity: T, val httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase

case class ListResponseEvent[T](req: RequestEvent, val entity: T, val httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase

package io.skysail.domain.resources

import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEventBase}
import io.skysail.domain.app.ApplicationApi

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class ListResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, List[T]] {

  def get(re: RequestEvent): ResponseEventBase = ListResponseEvent(re, getList(re))

  def getList(requestEvent: RequestEvent): List[T] = List()

  def reply(requestEvent: RequestEvent, answer: Future[List[_]]): Unit = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, answer)
      case Failure(f) => println(s"failure $f")
    }
  }

  def reply[U](requestEvent: RequestEvent, answer: Future[List[U]], c: List[U] => List[T]): Unit = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure $f")
    }
  }


}
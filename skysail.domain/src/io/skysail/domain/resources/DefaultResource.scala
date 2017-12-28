package io.skysail.domain.resources

import akka.actor.ActorSystem
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEventBase}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}
import io.skysail.domain.ResponseEvent

abstract class DefaultResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, List[T]] {

  final def doGetList(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! getList(requestEvent)
  }

  def doGetForPostUrl(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! ResponseEvent(requestEvent, null)
  }

  def get(re: RequestEvent): ResponseEventBase = ListResponseEvent(re, getList(re))

  def getList(requestEvent: RequestEvent): ListResponseEvent[List[T]] = ListResponseEvent(null, List())

  def post(requestEvent: RequestEvent): Unit

  def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit

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
package io.skysail.domain.resources

import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import io.skysail.domain.RequestEvent
import io.skysail.domain.ListResponseEvent
import io.skysail.domain.ResponseEvent
import io.skysail.domain.app.ApplicationApi

abstract class EntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {
  
  def get(requestEvent: RequestEvent): Unit

  def reply[U](requestEvent: RequestEvent, answer: Future[U], c: U => T) = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure ${f}")
    }
  }

  def entityReply[U](requestEvent: RequestEvent, answer: Future[U], c: U => T) = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure ${f}")
    }
  }

}
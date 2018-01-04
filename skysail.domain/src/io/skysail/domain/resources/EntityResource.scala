package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEvent}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class EntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case HttpMethods.GET => doGet(RequestEvent(cmd, self))
      case e: Any => log error "not supported"
    }
  }

  def getAsync(requestEvent: RequestEvent): Unit

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
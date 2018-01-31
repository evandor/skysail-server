package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain._
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class ListResource[S <: ApplicationApi, T: TypeTag, L:TypeTag] extends AsyncResource[S, T, L] {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case HttpMethods.GET => doGet(RequestEvent(cmd, controller))
      case e: Any => log error "not supported"
    }
  }

  def get(re: RequestEvent): ResponseEventBase = ListResponseEvent(re, getList(re))

  def getList(requestEvent: RequestEvent): List[T] = List()

//  def reply(requestEvent: RequestEvent, answer: Future[List[_]]): Unit = {
//    answer.onComplete {
//      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, answer)
//      case Failure(f) => println(s"failure $f")
//    }
//  }

  def reply[U](requestEvent: RequestEvent, answer: Future[List[U]], c: List[U] => List[T]): Unit = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, c.apply(s))
      case Failure(f) => println(s"failure $f")
    }
  }


}
package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEventBase}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}
import io.skysail.domain.ResponseEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.routes.{CreationMapping, EntityMapping, ListRouteMapping, UpdateMapping}
import org.slf4j.LoggerFactory

abstract class DefaultResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, List[T]] {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit = {
    // tag::methodMatch[]

    cmd.mapping match {
      case c: EntityMapping[_, _] => doGetEntity(RequestEvent(cmd, self))
      case c: ListRouteMapping[_, _] => doGetList(RequestEvent(cmd, self))
      case c: CreationMapping[_, _] => {
        cmd.ctx.request.method match {
          case HttpMethods.GET => doGetForPostUrl(RequestEvent(cmd, self))
          case HttpMethods.POST => post(RequestEvent(cmd, self))
          case _ => log warn s"unknown mapping"
        }
      }
      case c: UpdateMapping[_, _] => {
        cmd.ctx.request.method match {
          case HttpMethods.GET => doGetForPutUrl(RequestEvent(cmd, self))
          case HttpMethods.PUT => put(RequestEvent(cmd, self))
          case _ => log warn s"unknown mapping"
        }
      }
    }
    // end::methodMatch[]

  }

  final def doGetList(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! getList(requestEvent)
  }

  final def doGetEntity(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! ResponseEvent(requestEvent, getEntity(requestEvent).get)
  }

  def doGetForPostUrl(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! ResponseEvent(requestEvent, null)
  }

  def doGetForPutUrl(requestEvent: RequestEvent): Unit = {
    val optionalEntity = getEntity(requestEvent)
    requestEvent.controllerActor ! ResponseEvent(requestEvent, optionalEntity.get)
  }

  def get(re: RequestEvent): ResponseEventBase = ListResponseEvent(re, getList(re))

  def getEntity(re: RequestEvent): Option[T]

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
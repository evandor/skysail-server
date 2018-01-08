package io.skysail.domain.resources


import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{PutSupport, RequestEvent, ResponseEventBase}
import org.slf4j.LoggerFactory

import scala.reflect.runtime.universe._

abstract class PutResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] with PutSupport {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case HttpMethods.GET => doGet(RequestEvent(cmd, controller))
      case HttpMethods.PUT => asInstanceOf[PutSupport].put(RequestEvent(cmd, controller))
      case e: Any => log error "not supported"
    }
  }
  def get(requestEvent: RequestEvent): ResponseEventBase

  def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit

}
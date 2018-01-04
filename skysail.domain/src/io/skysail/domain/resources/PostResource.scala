package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{PostSupport, RequestEvent, ResponseEvent}
import org.slf4j.LoggerFactory

import scala.reflect.runtime.universe._

abstract class PostResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] with PostSupport {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case HttpMethods.GET => doGet(RequestEvent(cmd, self))
      case HttpMethods.POST => {
        asInstanceOf[PostSupport].post(RequestEvent(cmd, self))
      }
      case e: Any => log error "not supported"
    }
  }


  def get(requestEvent: RequestEvent): ResponseEvent[T]

  def post(requestEvent: RequestEvent): Unit

}
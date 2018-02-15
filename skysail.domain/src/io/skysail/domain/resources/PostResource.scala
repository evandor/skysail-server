package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{Transformer => _, _}
import org.slf4j.LoggerFactory

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

abstract class PostResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] with PostSupport {

  private val log = LoggerFactory.getLogger(this.getClass)

  val entityManifest: Manifest[T] = io.skysail.domain.Transformer.toManifest
  val entityClassTag: ClassTag[T] = io.skysail.domain.Transformer.toClassTag

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case HttpMethods.GET => doGet(RequestEvent(cmd, controller))
      case HttpMethods.POST => {
        asInstanceOf[PostSupport].post(RequestEvent(cmd, controller))
      }
      case e: Any => log error "not supported"
    }
  }


  def get(requestEvent: RequestEvent): ResponseEvent[T]

  def post(requestEvent: RequestEvent): Unit

  override def delete(requestEvent: RequestEvent): ResponseEventBase = null
}
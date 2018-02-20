package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{Transformer => _, _}
import org.slf4j.LoggerFactory

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

abstract class PostResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] {

  private val log = LoggerFactory.getLogger(this.getClass)

  val entityManifest: Manifest[T] = io.skysail.domain.Transformer.toManifest
  val entityClassTag: ClassTag[T] = io.skysail.domain.Transformer.toClassTag

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case HttpMethods.GET => doGet(RequestEvent(cmd, controller))
      case HttpMethods.POST => doPost(RequestEvent(cmd, controller))
      case e: Any => log error "not supported"
    }
  }

  final def put(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = {
    log error "PostResources cannot handle PUT Requests"
    throw new UnsupportedOperationException();
  }
  
  final def delete(requestEvent: RequestEvent): ResponseEventBase = {
    log error "PostResources cannot handle DELETE Requests"
    throw new UnsupportedOperationException();
  }

}
package io.skysail.domain.resources


import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods._
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{RequestEvent, ResponseEventBase, Transformer}
import org.slf4j.LoggerFactory

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

abstract class PutResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] {

  private val log = LoggerFactory.getLogger(this.getClass)

  val entityManifest: Manifest[T] = Transformer.toManifest
  val entityClassTag: ClassTag[T] = Transformer.toClassTag

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    cmd.ctx.request.method match {
      case GET => doGet(RequestEvent(cmd, controller))
      case PUT => doPut(RequestEvent(cmd, controller))
      case DELETE => doDelete(RequestEvent(cmd, controller))
      case e: Any => log error "not supported"
    }
  }

  final def post(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = {
    log error "PutResources cannot handle POST Requests"
    throw new UnsupportedOperationException()
  }
  
  def delete(requestEvent: RequestEvent): ResponseEventBase = {
    log warn "overwrite this method to support deletion on a PutResource"
    throw new UnsupportedOperationException()
  }
  
}
package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{RequestEvent, ResponseEvent, ResponseEventBase, Transformer}
import org.slf4j.LoggerFactory

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

abstract class EntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {

  private val log = LoggerFactory.getLogger(this.getClass)

  val entityManifest: Manifest[T] = Transformer.toManifest
  val entityClassTag: ClassTag[T] = Transformer.toClassTag

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    val req = RequestEvent(cmd, controller)
    val entity = getEntity(req)
    req.controllerActor ! ResponseEvent[T](req, entity.get)(entityManifest, entityClassTag)
  }
  
  def getEntity(re: RequestEvent): Option[T]

  override def get(requestEvent: RequestEvent): ResponseEventBase = { null }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = null
  
  def post(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = {
    log info s"noop implementation of POST in EntityResource"
    null
  }

  def put(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = {
    log info s"noop implementation of PUT in EntityResource"
    null
  }

}
package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEvent, Transformer}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class EntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {

  private val log = LoggerFactory.getLogger(this.getClass)

  val entityManifest: Manifest[T] = Transformer.toManifest

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    val req = RequestEvent(cmd, controller)
    val entity = getEntity(req)
    req.controllerActor ! ResponseEvent[T](req, entity.get, entityManifest)
  }
  
  def getEntity(re: RequestEvent): Option[T]

}
package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{RequestEvent, Transformer}

import scala.reflect.runtime.universe._

abstract class AsyncEntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {

  val entityManifest: Manifest[T] = Transformer.toManifest

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    getEntityAsync(RequestEvent(cmd, controller))
  }
  
  def getEntityAsync(re: RequestEvent): Unit

}
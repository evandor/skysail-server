package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import io.skysail.domain.RequestEvent
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand

import scala.reflect.runtime.universe._

abstract class AsyncEntityResource[S <: ApplicationApi, T: TypeTag, L: TypeTag] extends AsyncResource[S,T,L] {

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    getEntityAsync(RequestEvent(cmd, controller))
  }
  
  def getEntityAsync(re: RequestEvent): Unit

}
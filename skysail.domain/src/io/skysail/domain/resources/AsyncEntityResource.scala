package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEvent}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class AsyncEntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    getEntityAsync(RequestEvent(cmd, controller))
  }
  
  def getEntityAsync(re: RequestEvent): Unit

}
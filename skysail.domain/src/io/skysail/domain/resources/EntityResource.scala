package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{RequestEvent, ResponseEvent}
import org.slf4j.LoggerFactory

import scala.reflect.runtime.universe._

abstract class EntityResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T, String] {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    val req = RequestEvent(cmd, controller)
    req.controllerActor ! ResponseEvent(req, getEntity(req))
  }
  
  def getEntity(re: RequestEvent): Option[T]

//  def reply[U](requestEvent: RequestEvent, answer: Future[U], c: U => T) = {
//    answer.onComplete {
//      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, c.apply(s))
//      case Failure(f) => println(s"failure ${f}")
//    }
//  }
//
//  def entityReply[U](requestEvent: RequestEvent, answer: Future[U], c: U => T) = {
//    answer.onComplete {
//      case Success(s) => requestEvent.controllerActor ! ResponseEvent(requestEvent, c.apply(s))
//      case Failure(f) => println(s"failure ${f}")
//    }
//  }

}
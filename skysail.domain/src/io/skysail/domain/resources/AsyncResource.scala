package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.util.Timeout
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.{RequestEvent, ResponseEventBase, SkysailResource}

import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._

abstract class AsyncResource[S <: ApplicationApi, T: TypeTag] extends SkysailResource[S, List[T]] with ActorContextAware {

  implicit val timeout: Timeout = 3.seconds

  def handleRequest(cmd: ProcessCommand, self: ActorRef)(implicit system: ActorSystem): Unit

  final def doGet(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! get(requestEvent)
  }

  def get(requestEvent: RequestEvent): ResponseEventBase

  implicit class TypeDetector[T: TypeTag](related: SkysailResource[_, T]) {
    def getType(): Type = typeOf[T]
  }

}
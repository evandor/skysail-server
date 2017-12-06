package io.skysail.domain.resources


import akka.actor.ActorSystem
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{PutSupport, RequestEvent, ResponseEventBase}

import scala.reflect.runtime.universe._

abstract class PutResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] with PutSupport {

//  final def doGet(requestEvent: RequestEvent): Unit = {
//    requestEvent.controllerActor ! get(requestEvent)
//  }

  def get(requestEvent: RequestEvent): ResponseEventBase

  def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit

}
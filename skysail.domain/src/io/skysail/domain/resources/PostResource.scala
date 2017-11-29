package io.skysail.domain.resources

import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{PostSupport, RequestEvent, ResponseEvent}

import scala.reflect.runtime.universe._

abstract class PostResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] with PostSupport {

//  final def doGet(requestEvent: RequestEvent): Unit = {
//    requestEvent.controllerActor ! get(requestEvent)
//  }

  def get(requestEvent: RequestEvent): ResponseEvent[T]

  def post(requestEvent: RequestEvent): Unit

}
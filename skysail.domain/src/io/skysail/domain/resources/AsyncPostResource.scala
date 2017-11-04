package io.skysail.domain.resources

import akka.actor.ActorSystem

import scala.reflect.runtime.universe._
import io.skysail.domain.RequestEvent
import io.skysail.domain.PostSupport

abstract class AsyncPostResource[T: TypeTag] extends AsyncResource[T] with PostSupport {

  def get(requestEvent: RequestEvent): Unit

  def post(requestEvent: RequestEvent): Unit

}
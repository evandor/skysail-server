package io.skysail.domain.resources

import io.skysail.domain.{PostSupport, RequestEvent}

import scala.reflect.runtime.universe._

abstract class PostResource[T: TypeTag] extends AsyncResource[T] with PostSupport {

  def get(requestEvent: RequestEvent): Unit

  def post(requestEvent: RequestEvent): Unit

}
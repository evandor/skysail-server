package io.skysail.domain.resources


import io.skysail.domain.{PutSupport, RequestEvent}

import scala.reflect.runtime.universe._

abstract class PutResource[T: TypeTag] extends AsyncResource[T] with PutSupport {

  def get(requestEvent: RequestEvent): Unit

  def put(requestEvent: RequestEvent): Unit

}
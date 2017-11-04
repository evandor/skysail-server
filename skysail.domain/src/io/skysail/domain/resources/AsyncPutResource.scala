package io.skysail.domain.resources


import scala.reflect.runtime.universe._
import io.skysail.domain.RequestEvent
import io.skysail.domain.RequestEvent
import io.skysail.domain.PutSupport

abstract class AsyncPutResource[T: TypeTag] extends AsyncResource[T] with PutSupport {

  def get(requestEvent: RequestEvent): Unit

  def put(requestEvent: RequestEvent): Unit

}
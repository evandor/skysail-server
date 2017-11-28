package io.skysail.domain.resources


import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{PutSupport, RequestEvent}

import scala.reflect.runtime.universe._

abstract class PutResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, T] with PutSupport {

  def get(requestEvent: RequestEvent): Unit

  def put(requestEvent: RequestEvent): Unit

}
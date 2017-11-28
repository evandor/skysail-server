package io.skysail.domain.resources

import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{PostSupport, RequestEvent}

import scala.reflect.runtime.universe._

abstract class PostResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] with PostSupport {

  def get(requestEvent: RequestEvent): Unit

  def post(requestEvent: RequestEvent): Unit

}
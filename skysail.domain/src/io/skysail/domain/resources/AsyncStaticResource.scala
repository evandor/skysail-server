package io.skysail.domain.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.app.ApplicationApi
import scala.reflect.runtime.universe._

/**
  *
  */
// abstract class ListResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, List[T]] {
abstract class AsyncStaticResource[S <: ApplicationApi, T: TypeTag, L: TypeTag] extends AsyncResource[S,T,L] {

  def getAsync(requestEvent: RequestEvent): Unit

}
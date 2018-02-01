package io.skysail.domain.resources

import io.skysail.domain.{RequestEvent, Transformer}
import io.skysail.domain.app.ApplicationApi

import scala.reflect.runtime.universe._

/**
  *
  */
// abstract class ListResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, List[T]] {
abstract class AsyncStaticResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S,T] {

  val entityManifest: Manifest[T] = Transformer.toManifest

  def getAsync(requestEvent: RequestEvent): Unit

}
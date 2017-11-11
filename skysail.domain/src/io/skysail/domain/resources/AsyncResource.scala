package io.skysail.domain.resources

import io.skysail.domain.{RequestEvent, Resource}

import scala.reflect.runtime.universe._

abstract class AsyncResource[T: TypeTag] extends Resource[List[T]] with ActorContextAware {

  def get(requestEvent: RequestEvent): Unit

  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }

}
package io.skysail.domain.resources

import scala.reflect.runtime.universe._
import io.skysail.domain.Resource
import io.skysail.domain.RequestEvent

abstract class AsyncResource[T: TypeTag] extends Resource[List[T]] with ActorContextAware {

  def get(requestEvent: RequestEvent): Unit

  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }

}
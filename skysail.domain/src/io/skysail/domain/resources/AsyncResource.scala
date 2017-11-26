package io.skysail.domain.resources

import io.skysail.domain.{RequestEvent, SkysailResource}

import scala.reflect.runtime.universe._

abstract class AsyncResource[T: TypeTag] extends SkysailResource[List[T]] with ActorContextAware {

  def get(requestEvent: RequestEvent): Unit

  implicit class TypeDetector[T: TypeTag](related: SkysailResource[T]) {
    def getType(): Type = typeOf[T]
  }

}
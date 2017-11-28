package io.skysail.domain.resources

import akka.util.Timeout
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{RequestEvent, SkysailResource}

import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._

abstract class AsyncResource[S <: ApplicationApi, T: TypeTag] extends SkysailResource[S, List[T]] with ActorContextAware {

  implicit val timeout: Timeout = 1.seconds

  def get(requestEvent: RequestEvent): Unit

  implicit class TypeDetector[T: TypeTag](related: SkysailResource[_, T]) {
    def getType(): Type = typeOf[T]
  }

}
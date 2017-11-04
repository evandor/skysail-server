package io.skysail.domain.resources

import akka.actor.ActorRef
import io.skysail.domain.RequestEvent

abstract class AsyncStaticResource extends AsyncResource[Any] {

  def get(requestEvent: RequestEvent): Unit

}
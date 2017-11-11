package io.skysail.domain.resources

import io.skysail.domain.RequestEvent

/**
  *
  */
abstract class AsyncStaticResource extends AsyncResource[Any] {

  def get(requestEvent: RequestEvent): Unit

}
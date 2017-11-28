package io.skysail.domain.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.app.ApplicationApi

/**
  *
  */
abstract class AsyncStaticResource extends AsyncResource[ApplicationApi,Any] {

  def get(requestEvent: RequestEvent): Unit

}
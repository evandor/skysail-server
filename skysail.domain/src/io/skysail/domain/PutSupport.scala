package io.skysail.domain

trait PutSupport {
  def put(requestEvent: RequestEvent): Unit
}

package io.skysail.api.health

class AbstractHealthIndicator extends HealthIndicator {
  override def health(): Health = {
    Health(Status.UP)
  }
}

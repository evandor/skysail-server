package io.skysail.api.health

trait HealthIndicator {
  def health(): Health
}

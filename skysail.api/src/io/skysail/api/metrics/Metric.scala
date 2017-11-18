package io.skysail.api.metrics

trait Stoppable {
  def stop()
}

trait Markable {
  def mark()
}

sealed trait Metric

case class CounterMetric(cls: Class[_], identifier: String) extends Metric
case class HistogramMetric(cls: Class[_], identifier: String) extends Metric
case class MeterMetric(identifier: String) extends Metric

case class TimerMetric(val cls: AnyRef, identifier: String) extends Metric {
  private var stoppables = List[Stoppable]()
  def stop() = stoppables.foreach(_.stop)
  def setStoppables(stoppables: List[Stoppable]) = this.stoppables = stoppables
}

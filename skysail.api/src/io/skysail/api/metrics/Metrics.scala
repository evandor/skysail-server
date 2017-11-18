package io.skysail.api.metrics

trait Metrics {

  def registerTimer(metric: TimerMetric)
  def time(timerMetric: TimerMetric): Stoppable

  def registerMeter(metric: MeterMetric)
  def meter(identifier: MeterMetric)

  def registerCounter(metric: CounterMetric)
  def inc(metric: CounterMetric)
  def dec(metric: CounterMetric)

  def registerHistogram(metric: HistogramMetric)
  def update(metric: HistogramMetric, value: Long)
  //def getTimers(): List[TimerDataProvider]

}
package io.skysail.server.metrics

import io.skysail.api.metrics._
import org.slf4j.LoggerFactory

class SimpleMetrics extends Metrics {

  private val log = LoggerFactory.getLogger(this.getClass)

  val counters = scala.collection.mutable.Map[String, Long]()

  override def registerTimer(metric: TimerMetric): Unit = ???

  override def time(timerMetric: TimerMetric): Stoppable = ???

  override def registerMeter(metric: MeterMetric): Unit = ???

  override def meter(identifier: MeterMetric): Unit = ???

  override def registerCounter(metric: CounterMetric): Unit = {
    counters.put(determineName(metric), 0)
  }

  override def inc(metric: CounterMetric) {
    val name: String = determineName(metric)
    val old = counters.get(name).getOrElse(0L)
    counters.put(name, old + 1L)
  }

  override def dec(metric: CounterMetric): Unit = ???

  override def registerHistogram(metric: HistogramMetric): Unit = ???

  override def update(metric: HistogramMetric, value: Long): Unit = ???

  def dumpMetrics(): Unit = {
    log.info("Metrics:")
    log.info("========")
    counters.foreach(c => log info s"${c._1} => ${c._2}")

  }

  private def determineName(metric: CounterMetric) = {
    metric.cls.getName + "." + metric.identifier
  }


}

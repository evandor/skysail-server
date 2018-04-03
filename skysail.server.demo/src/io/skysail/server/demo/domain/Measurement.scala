package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.ListPayload

case class Measurement(
                        id: Option[String],
                        duration: Long,
                        timestamp: Long,
                        result: String
                      )
  extends Entity[String]

case class MeasurementList(payload: List[Measurement]) extends ListPayload[Measurement]

package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.ListPayload

case class Monitor2(id: Option[String], name: String, url: String)
  extends Entity[String]

case class Monitor2List(payload: List[Monitor2]) extends ListPayload[Monitor2]

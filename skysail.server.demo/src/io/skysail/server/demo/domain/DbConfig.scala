package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable}

case class DbConfig(id: Option[String], key: String, values: String) extends Entity[String] {
}


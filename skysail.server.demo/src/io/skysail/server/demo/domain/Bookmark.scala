package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable}

case class Bookmark(id: Option[String], title: String, url: String) extends Entity[String] with Linkable {
  override def _links = List(Link("self", "hier"))
}


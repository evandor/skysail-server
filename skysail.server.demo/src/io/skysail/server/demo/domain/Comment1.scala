package io.skysail.server.demo.domain

import javax.validation.constraints.Size

import io.skysail.api.ddd.Entity
import io.skysail.api.ui._

import scala.annotation.StaticAnnotation

// ---------------------------------

final class min(i: Long) extends StaticAnnotation

case class Comment1(

  id: Option[String],

  //@Size(min = 3)
  @min(1) comment: String
  
) extends Entity[String] with Linkable {
  override val _links: List[Link] = List()
}

case class Comment1List(payload: List[Comment1]) extends ListPayload[Comment1]

// ---------------------------------

case class Comment2(id: Option[String], comment: String) extends Entity[String] with Linkable {
  override val _links: List[Link] = List()
}

case class Comment2List(payload: List[Comment2]) extends ListPayload[Comment2] with Linkable {
  override val _links = List(
    ButtonLink("create-form", "create new Comment", "/demo/v1/comment2s/", style = "btn btn-outline-primary"))
}

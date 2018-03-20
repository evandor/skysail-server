package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui._
import javax.validation.constraints.Size

import scala.annotation.StaticAnnotation

// ---------------------------------
// (Minimal?) example with simple linked entity and ListPayload

case class Comment1(id: Option[String], comment: String) extends Entity[String] with Linkable {
  override val _links: List[Link] = List()
}

case class Comment1List(payload: List[Comment1]) extends ListPayload[Comment1]

// ---------------------------------


final class min(i: Long) extends StaticAnnotation

case class Foo(@min(1) c: String)

//@SerialVersionUID(1)

case class Comment2(

                     id: Option[String],

                     @Size(min = 3)
                     @min(1) comment: String

                   ) extends Entity[String] with Linkable {
  override val _links: List[Link] = List()
}

case class Comment2List(payload: List[Comment2]) extends ListPayload[Comment2]

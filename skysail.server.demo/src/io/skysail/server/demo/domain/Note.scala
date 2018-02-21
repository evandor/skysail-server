package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{ButtonLink, Link, Linkable}

/**
  * @param id
  * @param title
  * @param content
  */
case class Note(id: Option[String], title: String, content: String) extends Entity[String]

case class NoteList(
                         notes: List[Note]
                       ) extends Linkable {

  override val _links: List[Link] = List(
    ButtonLink("create-form", "create new Note", "/demo/v1/notes/", style = "btn btn-outline-primary")
  )

}

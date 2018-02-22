package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui._

/**
  * @param id
  * @param title
  * @param content
  */
case class Note(id: Option[String], title: String, content: String) extends Entity[String] with Linkable {

  override val _links: List[Link] = List(
    IconLink("show",   "fas fa-eye",   "/demo/v1/notes/${id}",  "color:#000066", "show entity"),
    IconLink("update", "fa fa-edit",   "/demo/v1/notes/${id}/", "", "update entity"),
    IconLink("delete", "fas fa-trash", "/demo/v1/notes/${id}/", "color:Tomato", "delete entity")
  )

}


case class NoteList(
                     payload: List[Note]
                   ) extends ListPayload[Note] with Linkable {

  override val _links: List[Link] = List(
    ButtonLink("create-form", "create new Note", "/demo/v1/notes/", style = "btn btn-outline-primary")
  )

}

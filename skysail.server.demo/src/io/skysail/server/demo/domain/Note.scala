package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity

/**
  * @param id
  * @param title
  * @param content
  */
case class Note(id: Option[String], title: String, content: String) extends Entity[String]

case class NoteList(
                         notes: List[Note]
                       )

package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity

/**
  * @param id
  * @param title
  * @param content
  */
case class Todo(id: Option[String], title: String, content: String) extends Entity[String]


case class TodoList(
                         todos: List[Todo]
                       )

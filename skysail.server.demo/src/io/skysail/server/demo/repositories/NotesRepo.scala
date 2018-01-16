package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Note

class NotesRepo(dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Note]))
  dbService.register(classOf[Note])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find() = dbService.findGraphs(classOf[Note], "SELECT * from " + DbService.tableNameFor(classOf[Note]))

  def find(id: String): Option[Note] = {
    val res = dbService.findGraphs(classOf[Note], s"SELECT * from ${DbService.tableNameFor(classOf[Note])} where id='${id}'") 
    if (res.size == 0) None else res.headOption
  }
}
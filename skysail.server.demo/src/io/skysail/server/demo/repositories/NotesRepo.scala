package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.server.demo.domain.Note

class NotesRepo(dbService: DbService, appModel: ApplicationModel) extends RepositoryApi[Note]{

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Note]))
  dbService.register(classOf[Note])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find(): List[Note] = dbService.findGraphs(
    classOf[Note], "SELECT * from " + DbService.tableNameFor(classOf[Note]),appModel)

  def find(id: String): Option[Note] = {
    val res = dbService.findGraphs(classOf[Note], s"SELECT * from ${DbService.tableNameFor(classOf[Note])} where id='${id}'",appModel)
    if (res.isEmpty) None else res.headOption
  }

  override def delete(id: String): Boolean = ???
}
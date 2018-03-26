package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.server.demo.domain.Todo

class TodosRepo(dbService: DbService, appModel: ApplicationModel) extends RepositoryApi[Todo] {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Todo]))
  dbService.register(classOf[Todo])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find(): List[Todo] = dbService.findGraphs(classOf[Todo], "SELECT * from " + DbService.tableNameFor(classOf[Todo]),appModel)

  def find(id: String): Option[Todo] = {
    val res = dbService.findGraphs(classOf[Todo], s"SELECT * from ${DbService.tableNameFor(classOf[Todo])} where id='${id}'",appModel)
    if (res.isEmpty) None else res.headOption
  }

  override def delete(id: String): Boolean = ???
}
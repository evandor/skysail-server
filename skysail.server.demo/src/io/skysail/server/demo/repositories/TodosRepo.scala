package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Todo

class TodosRepo(dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Todo]))
  dbService.register(classOf[Todo])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find() = dbService.findGraphs(classOf[Todo], "SELECT * from " + DbService.tableNameFor(classOf[Todo]))

  def find(id: String): Option[Todo] = {
    val res = dbService.findGraphs(classOf[Todo], s"SELECT * from ${DbService.tableNameFor(classOf[Todo])} where id='${id}'")
    if (res.size == 0) None else res.headOption
  }
}
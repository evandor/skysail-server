package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.server.demo.domain.Account

class AccountsRepo(dbService: DbService, appModel: ApplicationModel) extends RepositoryApi[Account] {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Account]))
  dbService.register(classOf[Account])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find() = dbService.findGraphs(classOf[Account], "SELECT * from " + DbService.tableNameFor(classOf[Account]), appModel)

  def find(id: String): Option[Account] = {
    val res = dbService.findGraphs(classOf[Account], s"SELECT * from ${DbService.tableNameFor(classOf[Account])} where id='${id}'", appModel)
    if (res.size == 0) None else res.headOption
  }

  override def delete(id: String): Boolean = ???
}
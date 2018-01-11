package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.server.demo.domain.Account

class AccountsRepo(dbService: DbService) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Account]))
  dbService.register(classOf[Account])

  def save(entity: Any): String = {
    dbService.persist(entity)
  }

  def find() = dbService.findGraphs(classOf[Account], "SELECT * from " + DbService.tableNameFor(classOf[Account]))

  def find(id: String): Option[Account] = {
    val res = dbService.findGraphs(classOf[Account], s"SELECT * from ${DbService.tableNameFor(classOf[Account])} where id='${id}'")
    if (res.size == 0) None else res.headOption
  }
}
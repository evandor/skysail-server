package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.server.demo.domain.DbConfig

class DbConfigsRepository(dbService: DbService) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[DbConfig]))
  dbService.register(classOf[DbConfig])

  def save(entity: Any): String = {
    dbService.persist(entity)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[DbConfig] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[DbConfig])
    dbService.findGraphs(classOf[DbConfig], sql) //, filter.getParams());
  }

  def find(id: String): Option[DbConfig] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[DbConfig])} where id='${id}'"
    val res = dbService.findGraphs(classOf[DbConfig], sql) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }
}
package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.DbConfig

class DbConfigsRepository(dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[DbConfig]))
  dbService.register(classOf[DbConfig])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[DbConfig] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[DbConfig])
    dbService.findGraphs(classOf[DbConfig], sql,appModel) //, filter.getParams());
  }

  def find(id: String): Option[DbConfig] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[DbConfig])} where id='${id}'"
    val res = dbService.findGraphs(classOf[DbConfig], sql,appModel) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }
}
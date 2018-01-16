package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.server.demo.domain.Pattern

class PatternRepository(dbService: DbService) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Pattern]))
  dbService.register(classOf[Pattern])

  def save(entity: Pattern): String = {

    dbService.persist(entity)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Pattern] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Pattern])
    //                + (!StringUtils.isNullOrEmpty(filter.getPreparedStatement()) ? " WHERE " + filter.getPreparedStatement()
    //                        : "")
    //                + " " + limitClause(pagination);
    //pagination.setEntityCount(count(filter));
    //println("executing sql " + sql)
    dbService.findGraphs(classOf[Pattern], sql) //, filter.getParams());
  }

  def find(id: String): Option[Pattern] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[Pattern])} where id='${id}'"
    //println("executing sql " + sql)
    val res = dbService.findGraphs(classOf[Pattern], sql) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }
}
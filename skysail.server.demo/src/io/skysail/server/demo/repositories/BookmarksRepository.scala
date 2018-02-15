package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Bookmark

class BookmarksRepository (dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Bookmark]))
  dbService.register(classOf[Bookmark])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Bookmark] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Bookmark])
    //                + (!StringUtils.isNullOrEmpty(filter.getPreparedStatement()) ? " WHERE " + filter.getPreparedStatement()
    //                        : "")
    //                + " " + limitClause(pagination);
    //pagination.setEntityCount(count(filter));
    //println("executing sql " + sql)
    dbService.findGraphs(classOf[Bookmark], sql) //, filter.getParams());
  }

  def find(id: String): Option[Bookmark] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[Bookmark])} where id='${id}'"
    //println("executing sql " + sql)
    val res = dbService.findGraphs(classOf[Bookmark], sql) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }

  def delete(id: String): Boolean = {
    dbService.delete(classOf[Bookmark],id)
  }
}
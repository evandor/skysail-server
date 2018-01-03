package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.server.demo.domain.Note

class NotesRepo(dbService: DbService) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Note]))
  dbService.register(classOf[Note])

  def save(entity: Any): String = {
    dbService.persist(entity)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Note] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Note])
    dbService.findGraphs(classOf[Note], sql) //, filter.getParams());
  }

  def find(id: String): Option[Note] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[Note])} where id='${id}'"
    val res = dbService.findGraphs(classOf[Note], sql) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }
}
package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Pattern
import io.skysail.server.demo.domain.OrientPattern

class PatternRepository(dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Pattern]))
  dbService.register(classOf[Pattern])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Pattern] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Pattern])
    //dbService.findGraphs(classOf[Pattern], sql) //, filter.getParams());
    val orientPattern = dbService.findByClass(classOf[OrientPattern])
    println(orientPattern)
    List()
  }

  def find(id: String): Option[Pattern] = {
    Some(dbService.findById(classOf[Pattern], id))
  }
}
package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Pattern
import io.skysail.server.demo.domain.OrientPattern
import io.skysail.server.demo.domain.Account

class PatternRepository(dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Pattern]))
  dbService.register(classOf[Pattern])

  def save(entity: Any) = dbService.persist(entity, appModel)
  def find() = dbService.findByClass(classOf[Pattern])
  def find(id: String) = Some(dbService.findById(classOf[Pattern], id))

}
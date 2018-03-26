package io.skysail.db.orientdb.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.repositories.RepositoryApi

abstract class ResourceRepository[T:Manifest](cls: Class[T], dbService: DbService, appModel: ApplicationModel) extends RepositoryApi[T] {

  dbService.createWithSuperClass("V", DbService.tableNameFor(cls))
  dbService.register(cls)

  def save(entity: Any): String = dbService.persist(entity, appModel)

  def getEntityTemplate: T

  def find(): List[T] = {
    val sql = "SELECT * from " + DbService.tableNameFor(cls)
    dbService.findGraphs2(getEntityTemplate, sql, appModel)
  }

  def find(id: String): Option[T] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='$id'"
    val res: Seq[T] = dbService.findGraphs2(getEntityTemplate, sql, appModel)
    if (res.isEmpty) None else res.headOption
  }

  def delete(id: String): Boolean = dbService.delete(cls, id)
}

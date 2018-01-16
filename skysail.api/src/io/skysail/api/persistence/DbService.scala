package io.skysail.api.persistence

import io.skysail.domain.model.ApplicationModel

object DbService {
  def tableNameFor(cls: Class[_]): String = cls.getName.replace(".", "_")
}

trait DbService {

  // tag::methods[]
  def createWithSuperClass(superClass: String, vertices: String*)
  def register(classes: Class[_]*)
  def persist(entity: Any, appModel: ApplicationModel): String
  def findGraphs[T:Manifest](cls: Class[T], sql: String): List[T]
  // end::methods[]
}

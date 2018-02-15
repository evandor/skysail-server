package io.skysail.server.demo

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Bookmark

class DummyDbService extends DbService() {

  private var bookmarks = scala.collection.mutable.Map[String, Bookmark]()

  override def createWithSuperClass(superClass: String, vertices: String*): Unit = {}

  override def register(classes: Class[_]*): Unit = {}

  override def persist(entity: Any, appModel: ApplicationModel): String = {
    val bm: Bookmark = entity.asInstanceOf[Bookmark]
    bookmarks += bm.id.get -> bm
    return bm.id.get
  }

  override def findGraphs[T: Manifest](cls: Class[T], sql: String): List[T] = {
    bookmarks.values.map(b => b.asInstanceOf[T]).toList
  }

  def findByClass[T](cls: Class[T])(implicit evidence$3: Manifest[T]): List[T] = {
    ???
  }

  def findById[T](cls: Class[T], id: String)(implicit evidence$2: Manifest[T]): T = {
    ???
  }

  override def delete[T: Manifest](cls: Class[T], id: String): Boolean = ???
}

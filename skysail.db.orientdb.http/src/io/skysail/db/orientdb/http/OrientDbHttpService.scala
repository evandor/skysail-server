package io.skysail.db.orientdb.http

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel

import com.softwaremill.sttp._

class OrientDbHttpService(url: String, user: String, pass: String) extends DbService {

  implicit val backend = HttpURLConnectionBackend()
  
  def register(classes: Class[_]*): Unit = {}
  def createWithSuperClass(superClass: String, vertices: String*): Unit = {}

  def findById[T](cls: Class[T], id: String)(implicit evidence$2: Manifest[T]): T = {
    ???
  }

  def persist(entity: Any, appModel: ApplicationModel): String = {
    ???
  }

  def findByClass[T](cls: Class[T])(implicit evidence$3: Manifest[T]): List[T] = {
    val r = sttp
      .get(uri"http://85.25.22.126:2480/document/skysail_demo_int/12:0/*:-1")
      .auth.basic("admin", "23dela11_admin")
    val s = backend.send(r);
    print(s.body)
    List()
  }

  def findGraphs[T](cls: Class[T], sql: String)(implicit evidence$1: Manifest[T]): List[T] = {
    ???
  }
}
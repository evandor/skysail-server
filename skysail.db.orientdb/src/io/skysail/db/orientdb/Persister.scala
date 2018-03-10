package io.skysail.db.orientdb

import com.fasterxml.jackson.databind.ObjectMapper
import com.tinkerpop.blueprints.impls.orient.{OrientGraph, OrientVertex}
import io.skysail.domain.Transformer
import io.skysail.domain.model.ApplicationModel
import org.json4s
import org.json4s._
import org.slf4j.LoggerFactory

object Persister {
  def getMethodName(prefix: String, key: String): String = {
    return new StringBuilder(prefix).append(key.substring(0, 1).toUpperCase()).append(key.substring(1)).toString()
  }
}

class Persister(db: OrientGraph, appModel: ApplicationModel, optionalVertex: Option[OrientVertex] = None) {

  private val log = LoggerFactory.getLogger(this.getClass)

  val mapper = new ObjectMapper()

  val helper = new OrientDbHelper(appModel)

  val edgeHandler = new EdgeHandler(/*(identifiable) -> execute(identifiable), */ db,helper)

  def persist(entity: Any): String = {
    runInTransaction(entity).toString
  }

  private def runInTransaction(entity: Any): OrientVertex = {
    try {
      val result = execute(entity);
      db.commit();
      return result;
    } catch {
      case e: Exception =>
        db.rollback();
        throw new RuntimeException("Database Problem, rolled back transaction", e);
    } finally {
      db.shutdown();
    }
  }

  def execute(entity: Any): OrientVertex = {
    val vertex = determineVertex(entity);
    try {
      implicit val formats = DefaultFormats

      val em = appModel.entityModelFor(entity.getClass)
      val x: json4s.JValue = Transformer.beanToJson2(entity,em.get.dfs)

      //val e = Extraction.decompose(entity).asInstanceOf[JObject]
      //log info s"AST: $e"
      x.asInstanceOf[JObject].obj.foreach(jField => helper.setPropertyOrCreateEdge(entity.getClass, vertex, jField, edgeHandler))
      vertex;
    } catch {
      case e: Exception =>
        //log.error(e.getMessage(), e);
        throw new RuntimeException("Problem when persisting entity", e);
    }
  }

  private def determineVertex(entity: Any): OrientVertex = {
    var vertex: OrientVertex = null
    if (optionalVertex.isDefined) {
      vertex = db.getVertex(optionalVertex.get.getIdentity);
    } else {
      vertex = db.addVertex("class:" + entity.getClass().getName().replace(".", "_"), Nil: _*);
    }
    vertex;
  }

  private def isProperty(entity: Any, key: String) = !appModel.entityRelationExists(entity.getClass, key)
  

}
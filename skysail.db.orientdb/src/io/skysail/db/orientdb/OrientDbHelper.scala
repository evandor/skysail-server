package io.skysail.db.orientdb

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import io.skysail.domain.model.ApplicationModel
import org.json4s.{JField, JInt, JString}
import org.slf4j.LoggerFactory

class OrientDbHelper(appModel: ApplicationModel) {

  private val log = LoggerFactory.getLogger(this.getClass)

  def setPropertyOrCreateEdge(entityClass: Class[_], vertex: OrientVertex, jValue: JField, edgeHandler: EdgeHandler) = {
    val key = jValue._1
    if (isProperty(entityClass, key)) {
      //if (props.get(key) != null && !("class".equals(key))) {
      setProperty(vertex, jValue)
      //}
    } else {
      try {
        edgeHandler.handleEdges(entityClass, vertex, jValue, key)
      } catch {
        case e: Exception => log.error(e.getMessage(), e);
      }
    }
  }

  def setProperty(vertex: OrientVertex, jValue: JField): Unit = {
    jValue._2 match {
      case string: JString =>
        log info s"setting Property('${jValue._1}','${string.s}')"
        vertex.setProperty(jValue._1, string.s)
      case jInt: JInt =>
        log info s"setting Property('${jValue._1}','${jInt.num}')"
        val v = jInt.num.toInt
        vertex.setProperty(jValue._1, v)
      case _: Any => log warn s"no idea what to do, trying to match '${jValue._1}: ${jValue._2}'"
    }
  }

  private def isProperty(entityClass: Class[_], key: String) = !appModel.entityRelationExists(entityClass, key)
}

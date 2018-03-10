package io.skysail.db.orientdb

import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import org.json4s.JsonAST.JField
import org.json4s.JsonAST.JObject
import org.json4s.JsonAST.JString
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientDynaElementIterable
import io.skysail.api.persistence.DbService

class EdgeHandler(db: OrientGraph, helper: OrientDbHelper) {

  def handleEdges(entityClass: Class[_], vertex: OrientVertex, jValue: JField, key: String):Unit = {
    val field = entityClass.getDeclaredField(key);
    val theType = field.getType();
    addReference(vertex, field.getType(), jValue, key /*, edges*/ );
  }

  def addReference(vertex: OrientVertex, cls: Class[_], jValue: JField, key: String):Unit = {
    val jObject = jValue._2.asInstanceOf[JObject]
    val id = jObject.obj.filter(o => o._1 == "id").head._2.asInstanceOf[JString].s
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='${id}'"
    val oCommand = new OCommandSQL(sql)
    val execute = db.command(oCommand)
    val results: OrientDynaElementIterable = execute.execute()
    if (!results.iterator().hasNext) {
      val target = db.addVertex("class:" + cls.getName().replace(".", "_"), Nil: _*);

      jObject.obj.foreach(jField => helper.setPropertyOrCreateEdge(cls, target, jField, this))

      db.addEdge(null, vertex, target, key);
    } else {
      val   target = results.iterator().next().asInstanceOf[OrientVertex]
      if (target != null) {
        db.addEdge(null, vertex, target, key);
      }
    }
  }

}
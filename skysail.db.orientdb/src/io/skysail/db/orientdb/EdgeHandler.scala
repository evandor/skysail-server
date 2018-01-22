package io.skysail.db.orientdb

import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import org.json4s.JsonAST.JField
import org.json4s.JsonAST.JObject
import org.json4s.JsonAST.JString
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.impls.orient.OrientDynaElementIterable
import io.skysail.api.persistence.DbService

class EdgeHandler(db: OrientGraph) {

  def handleEdges(entity: Any, vertex: OrientVertex, jValue: JField, key: String) = {
    val field = entity.getClass().getDeclaredField(key);
    val theType = field.getType();
    //val edges = properties.get(key);
    //if (edges == null) {
    //return ;
    //}

    addReference(vertex, field.getType(), jValue, key /*, edges*/ );

  }

  def addReference(vertex: OrientVertex, cls: Class[_], jValue: JField, key: String) = {    
    val id = jValue._2.asInstanceOf[JObject].obj.filter(o => o._1 == "id").head._2.asInstanceOf[JString].s
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='${id}'"
    //println(sql)
    val oCommand = new OCommandSQL(sql)
    val execute = db.command(oCommand)
    val results: OrientDynaElementIterable = execute.execute()
    val target = results.iterator().next().asInstanceOf[OrientVertex]
    if (target != null) {
      db.addEdge(null, vertex, target, key);
    }
  }

}
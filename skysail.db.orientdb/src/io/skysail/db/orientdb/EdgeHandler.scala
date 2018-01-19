package io.skysail.db.orientdb

import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import org.json4s.JsonAST.JField

class EdgeHandler(db: OrientGraph) {

  def handleEdges(entity: Any, vertex: OrientVertex, jValue: JField, key: String) = {
    val field = entity.getClass().getDeclaredField(key);
    val theType = field.getType();
    //val edges = properties.get(key);
    //if (edges == null) {
    //return ;
    //}

    addReference(vertex, jValue, key /*, edges*/ );

  }

  def addReference(vertex: OrientVertex, jValue: JField, key: String) = {
    val target = db.getVertex(jValue._2.toString());
    if (target != null) {
      db.addEdge(null, vertex, target, key);
    }
  }

}
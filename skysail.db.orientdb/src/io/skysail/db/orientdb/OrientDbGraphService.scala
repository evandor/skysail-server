package io.skysail.db.orientdb

import com.orientechnologies.orient.`object`.db.OObjectDatabaseTx
import com.orientechnologies.orient.core.db.OPartitionedDatabasePool
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.{OCommandSQL, OSQLEngine}
import com.orientechnologies.orient.graph.sql.OGraphCommandExecutorSQLFactory
import com.orientechnologies.orient.graph.sql.functions.OGraphFunctionFactory
import com.tinkerpop.blueprints.impls.orient._
import io.skysail.api.ddd.Entity
import io.skysail.api.persistence.DbService
import io.skysail.domain.Transformer
import io.skysail.domain.model.ApplicationModel
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

class OrientDbGraphService(url: String, user: String, pass: String) extends DbService {

  private var log = LoggerFactory.getLogger(this.getClass)

  var graphDbFactory: OrientGraphFactory = null

  log info s"activating ${this.getClass().getName()}"

  //http://stackoverflow.com/questions/30291359/orient-db-unable-to-open-any-kind-of-graph
  if (url.startsWith("memory:")) {
    // https://github.com/orientechnologies/orientdb/issues/5105
    // com.orientechnologies.common.util.OClassLoaderHelper
    new OGraphCommandExecutorSQLFactory()
    new OrientGraphNoTx(url)
  }

  graphDbFactory = new OrientGraphFactory(url, user, pass).setupPool(1, 10)

  val graphFunctions = new OGraphFunctionFactory()
  val names = graphFunctions.getFunctionNames().asScala

  for (name <- names) {
    OSQLEngine.getInstance().registerFunction(name, graphFunctions.createFunction(name))
    val function = OSQLEngine.getInstance().getFunction(name)
    if (function != null) {
      log debug s"ODB graph function [${name}] is registered: [${function.getSyntax()}]"
    } else {
      log warn "ODB graph function [{$name}] NOT registered!!!"
    }
  }

  def createWithSuperClass(superClass: String, vertices: String*) = {
    val objectDb = getObjectDb()
    vertices.foreach(v => {
      if (objectDb.getMetadata().getSchema().getClass(v) == null) {
        val vertexClass = objectDb.getMetadata().getSchema().getClass(superClass)
        objectDb.getMetadata().getSchema().createClass(v).setSuperClass(vertexClass)
      }
    })
  }

  private def getObjectDb(): OObjectDatabaseTx = {
    val opDatabasePool = new OPartitionedDatabasePool(url, user, pass)
    val oDatabaseDocumentTx = opDatabasePool.acquire()
    new OObjectDatabaseTx(oDatabaseDocumentTx)
  }

  def register(classes: Class[_]*) = {
    val db = getObjectDb()
    try {
      classes.foreach(cls => {
        log.debug("registering class '{}' @ orientDB", cls)
        db.getEntityManager().registerEntityClass(cls)
      })
    } finally {
      db.close()
    }
  }

  def persist(entity: Any, appModel: ApplicationModel): String = {
    if (entity.isInstanceOf[Entity[_]] && entity.asInstanceOf[Entity[_]].id.isDefined) {
      val r = findByBusinessId(entity.getClass(), entity.asInstanceOf[Entity[_]].id.get.toString())
      new Persister(getGraphDb(), appModel, r).persist(entity)
    } else {
      new Persister(getGraphDb(), appModel).persist(entity)
    }

  }

  def findGraphs[T: Manifest](cls: Class[T], sql: String /*, Map<String, Object> params*/, appModel: ApplicationModel ): List[T] = {
    val results = executeCommand[T](sql)
    val result = scala.collection.mutable.ListBuffer[T]()
    results.asScala.foreach(v => {
      try {
        val r = v.asInstanceOf[OrientVertex]
        result += documentToBeanGraph(r.getRecord(), cls, appModel)
      } catch {
        case e: Throwable => log warn s"not able to create bean out of $v: ${e.getMessage}"
      }
    })

    result.toList
  }

  def findGraphs2[T: Manifest](template: T, sql: String, appModel: ApplicationModel): List[T] = {
    val results = executeCommand[T](sql)
    val result = scala.collection.mutable.ListBuffer[T]()
    results.asScala.foreach(v => {
      try {
        val r = v.asInstanceOf[OrientVertex]
        result += documentToBeanWithTemplate(r.getRecord(), template)
      } catch {
        case e: Throwable => log warn s"not able to create bean out of $v: ${e.getMessage}"
      }
    })

    result.toList
  }

  // TODO return Option
  def findById[T: Manifest](cls: Class[T], id: String): T = {
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='${id}'"
    //println("executing sql " + sql)
    val res = executeCommand(sql) //, filter.getParams());
    val r = res.iterator().next().asInstanceOf[OrientVertex]
    val result = documentToBean(r.getRecord, cls)
    result.asInstanceOf[T]
  }

  def findByClass[T: Manifest](cls: Class[T]): List[T] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)}"
    println("executing sql " + sql)
    val res = executeCommand(sql) //, filter.getParams());
    val result = scala.collection.mutable.ListBuffer[T]()
    for (i <- res.iterator().asScala) {
      result += documentToBean(i.asInstanceOf[OrientVertex].getRecord, cls);
    }
    result.toList
  }

  private def getGraphDb(): OrientGraph = {
    val graph = graphDbFactory.getTx()
    // https://github.com/orientechnologies/orientdb/issues/2722
    graph.setStandardElementConstraints(false)
    graph
  }

  private def documentToBean[T: Manifest](doc: ODocument, cls: Class[T]): T = {
    println()
    println("Doc: " + doc)
    val json = doc.toJSON("fetchPlan:*:2") /*rid,version,*/
    Transformer.jsonStringToBean2(json, cls)
  }

  private def documentToBeanGraph[T: Manifest](doc: ODocument, cls: Class[T], appModel: ApplicationModel): T = {
    //Transformer.jsonStringToBean(doc.toJSON("fetchPlan:*:-1"))
    println("1" + doc.toJSON("fetchPlan:*:2"))
    println("2" + doc.toJSON("fetchPlan:*:-1"))
    Transformer.jsonStringToBean3(doc.toJSON("fetchPlan:*:-1"), cls, appModel)
  }

  private def documentToBeanWithTemplate[T: Manifest](doc: ODocument, template: T): T = {
    Transformer.jsonStringToBeanWithTemplate(doc.toJSON("fetchPlan:*:-1"), template)
  }

  private def executeCommand[T](sql: String): OrientDynaElementIterable = {
    val graph = getGraphDb()
    val oCommand = new OCommandSQL(sql)
    oCommand.setFetchPlan("[*]*:-1")
    val execute = graph.command(oCommand)
    execute.execute()
  }

  override def delete[T: Manifest](cls: Class[T], id: String): Boolean = {
    //    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='${id}'"
    //    val res = executeCommand(sql) //, filter.getParams());
    //    val r = res.iterator().next().asInstanceOf[OrientVertex]
    val r = findByBusinessId(cls, id).get
    val graphDb = getGraphDb()
    val sql2 = s"DELETE VERTEX ${DbService.tableNameFor(cls)} WHERE @rid=${r.getId}"
    println("executing sql2 " + sql2)
    graphDb.command(new OCommandSQL(sql2)).execute()
    graphDb.commit()
    true
  }

  private def findByBusinessId[T: Manifest](cls: Class[T], id: String): Option[OrientVertex] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='${id}'"
    val i = executeCommand(sql).iterator() //, filter.getParams());
    if (i.hasNext()) Some(i.next().asInstanceOf[OrientVertex]) else None
  }

}
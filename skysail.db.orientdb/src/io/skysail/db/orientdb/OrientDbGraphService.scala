package io.skysail.db.orientdb

import com.orientechnologies.orient.`object`.db.OObjectDatabaseTx
import com.orientechnologies.orient.core.db.OPartitionedDatabasePool
import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.{ OCommandSQL, OSQLEngine }
import com.orientechnologies.orient.graph.sql.OGraphCommandExecutorSQLFactory
import com.orientechnologies.orient.graph.sql.functions.OGraphFunctionFactory
import com.tinkerpop.blueprints.impls.orient._
import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
import com.orientechnologies.orient.core.index.OIndexes
import com.orientechnologies.orient.core.db.ODatabaseDocumentInternal
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal
import com.orientechnologies.orient.core.command.traverse.OTraverse
import com.orientechnologies.orient.core.id.ORecordId
import com.orientechnologies.orient.core.sql.filter.OSQLPredicate

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
    new Persister(getGraphDb(), appModel).persist(entity)
  }

  def findGraphs[T: Manifest](cls: Class[T], sql: String /*, Map<String, Object> params*/ ): List[T] = {
    val results = executeCommand[T](sql)
    val result = scala.collection.mutable.ListBuffer[T]()
    results.asScala.foreach(v => {
      try {
        val r = v.asInstanceOf[OrientVertex]
        result += documentToBean(r.getRecord(), cls)
      } catch {
        case e: Throwable => log warn s"not able to create bean out of $v: ${e.getMessage}"
      }
    })

    result.toList
  }

  def findById[T: Manifest](cls: Class[T], id: String): T = {

    val sql = s"SELECT * from ${DbService.tableNameFor(cls)} where id='${id}'"
    println("executing sql " + sql)
    val res = executeCommand(sql) //, filter.getParams());
    //if (res.size == 0) None else res.headOption
    val v = res.iterator().next().asInstanceOf[OrientVertex]
    println(v)
    val db: ODatabaseDocumentInternal = getObjectDb().getUnderlying();
    ODatabaseRecordThreadLocal.INSTANCE.set(db);
    val predicate: OTraverse = new OTraverse().target(new ORecordId(v.getId.toString())).fields("out", "int").limit(1)
      .predicate(new OSQLPredicate("$depth <= 3"));
    val document = predicate.iterator().next().asInstanceOf[ODocument]
    //beanCache.clear();
    documentToBean(document, cls)
  }

  def findByClass[T: Manifest](cls: Class[T]): List[T] = {
//    val db: ODatabaseDocumentInternal = getObjectDb().getUnderlying();
//    ODatabaseRecordThreadLocal.INSTANCE.set(db);
//    val t = new OTraverse()
//      .target(db.browseClass(DbService.tableNameFor(cls)).iterator())
//      .fields("out", "in")
//      .limit(10)
//      .predicate(new OSQLPredicate("1 <= 3"))
//      
//     val result = scala.collection.mutable.ListBuffer[T]()
//    val res = for (i <- t.iterator().asScala) 
//    {
//      result += documentToBean(i.asInstanceOf[ODocument], cls);
//    } 
    val sql = s"SELECT * from ${DbService.tableNameFor(cls)}"
    println("executing sql " + sql)
    val res = executeCommand(sql) //, filter.getParams());
    //if (res.size == 0) None else res.headOption
//    val v = res.iterator().next().asInstanceOf[OrientVertex]
//    println(v)
    
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
    //populateProperties(document.toMap(), bean, new SkysailBeanUtils(bean, Locale.getDefault(), appService));
    //populateOutgoingEdges(document, bean);
    //populateIngoingEdge(document, bean);
    //doc.fieldNames().foreach(fieldName => )
    val json = doc.toJSON("fetchPlan:*:-1")
    println(json)
    val ast = parse(json)
    println(ast)
    implicit val formats = DefaultFormats
    // println("AST" + ast)
    ast.extract[T]
  }

  private def executeCommand[T](sql: String): OrientDynaElementIterable = {
    val graph = getGraphDb()
    val oCommand = new OCommandSQL(sql)
    oCommand.setFetchPlan("*:-1")
    val execute = graph.command(oCommand)
    execute.execute()
  }

}
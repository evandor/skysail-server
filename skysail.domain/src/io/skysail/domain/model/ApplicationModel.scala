package io.skysail.domain.model

import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatcher

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import io.skysail.domain.SkysailResource
import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.RouteMappingI
import org.json4s.{DefaultFormats, Formats}
import org.slf4j.LoggerFactory

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._

/**
  * This is the root class of skysail's core domain, providing models of "skysail applications",
  * which aggregate resources, their associated entities (together with the entities' fields),
  * links between the resources and many more.
  *
  * A real-life ApplicationModel is setup by creating an instance and then adding resource models
  * together with their respective paths using "addResourceModel". A resource model describes the
  * resource responsible for the associated path together with relations amongst controllers. Furthermore,
  * it knows about the entity class related with the controller.
  *
  * @constructor create a new application model, identified by its name.
  * @param name       the application's (unique and descriptive) name
  * @param apiVersion the applications API version, can be null
  *
  */
// TODO case class or not? Use copies when adding resourceModels etc
case class ApplicationModel(
                             name: String,
                             apiVersion: ApiVersion,
                             description: String) {

  require(name != null, "The application's name should be unique and must not be null")
  require(name.trim().length() > 0, "The application's name must not be empty")

  private val log = LoggerFactory.getLogger(this.getClass)

  /** the id of this application model, e.g. "demo/v1" */
  val id: String = if (apiVersion == null) name else s"$name/${apiVersion.toString()}"

  /** the akka root route for this application */
  val appRoute: PathMatcher[Unit] = {
    log info s"attaching $name with apiVersion $apiVersion"
    if (apiVersion == null) PathMatcher(name) else name / apiVersion.toString()
  }

  /** The list of resourceModels of this applicationModel. */
  val resourceModels: ListBuffer[ResourceModel] = scala.collection.mutable.ListBuffer[ResourceModel]()

  /** The map between */
  private val entityModelsMap: mutable.LinkedHashMap[String, EntityModel] = scala.collection.mutable.LinkedHashMap()

  // TODO
  def linkFor(clsName: String): Option[String] = {
    val res = resourceModels.map(m => m.linkFor(clsName)).filter(l => l.isDefined).map(l => l.get)
    if (res.isEmpty) None else Some("/" + id + res.head)
  }

  /**
    * Adds a resource model for a given route mapping.
    *
    * @param routeMapping the mapping is used to create the ResourceModel
    * @return
    */
  def addResourceModel(routeMapping: RouteMappingI[_, _]): Option[Type] = {
    require(routeMapping.resourceClass != null, "The routeMapping's resource class must not be null")

    log debug s"mapping '${appPath()}${routeMapping.path}' to '${routeMapping.resourceClass}[${routeMapping.getEntityType()}]'"

    val resourceModel = ResourceModel(routeMapping)

    if (resourceModels.exists(_.routeMapping.path == resourceModel.routeMapping.path)) {
      log.info(s"trying to add entity ${resourceModel.routeMapping.path} again, ignoring...")
      return None
    }
    val entityClass: universe.Type = resourceModel.entityClass
    addEntity(entityClass)
//    if (entityModelsMap.get(entityClass.toString).isEmpty) {
//      entityModelsMap += entityClass.toString -> EntityModel(entityClass)
//    }
    resourceModels += resourceModel
    build()
    Some(resourceModel.entityClass)
  }

  def addEntity[T](entityClass: Class[T]): Unit = {
    addEntity(entityClass, DefaultFormats)
  }

  def addEntity[T](entityClass: Class[T], dfs: Formats): Unit = {
    val rut: universe.Type = getType(entityClass)
    addEntity(rut, dfs)
  }

  def addEntity(entityClass: Type): Unit = {
    addEntity(entityClass, DefaultFormats)
  }

  def addEntity(entityClass: Type, dfs: Formats): Unit = {
    if (entityModelsMap.get(entityClass.toString).isEmpty) {
      entityModelsMap += entityClass.toString -> EntityModel(entityClass, dfs)
    }
    build()
  }

  private def controllerModelFor(cls: Class[_ <: SkysailResource[_, _]]): Option[ResourceModel] = {
    resourceModels.find { model => model.routeMapping.resourceClass == cls }
  }

  def entities(): collection.Set[String] = entityModelsMap.keySet

  def entityModelFor(cls: Class[_]): Option[EntityModel] = entityModelFor(cls.getName)

  def entityModelFor(clsName: String): Option[EntityModel] = entityModelsMap.get(clsName)

  def entityModelFor(ssr: SkysailResource[_, _]): Option[EntityModel] = {
    val resModel = controllerModelFor(ssr.getClass)
    if (resModel.isEmpty) {
      None
    }

    entityModelsMap.values.find(v => v.entityClass == resModel.get.entityClass)
  }

  def entityModelFor(url: Uri): Option[EntityModel] = {
    val appSegment = if (apiVersion == null) s"/$name" else s"/$name/$apiVersion"
    val resourceModel = resourceModels.find(_.matchPath(url, appSegment))
    if (resourceModel.isDefined) Some(resourceModel.get.entityModel) else None
  }

  /**
    * @return the context path of the application, e.g. "/testapp/v2" or "/appwithoutversion".
    */
  def appPath(): String = "/" + name + (if (apiVersion != null) "/" + apiVersion.toString else "")

  def entityRelationExists(entityClass: Class[_], memberKey: String): Boolean = {
    entityRelationFields(entityClass).contains(memberKey)
  }

  /**
    * Returns the names of the fields of the provided entityClass which will be
    * treated as 'relations', i.e. as an edge to a different node.
    *
    * @param entityClass
    * @return
    */
  def entityRelationFields(entityClass: Class[_]): List[String] = {

    val fieldsAsPairs = for (field <- entityClass.getDeclaredFields) yield {
      field.setAccessible(true)
    }

    val es: Seq[String] = entities().toList

    println (entityClass.getDeclaredFields
      .map(f => (f.getName, f.getType.getName.replace("$",".")))
      .toList)

    entityClass.getDeclaredFields
      .map(f => (f.getName, f.getType.getName.replace("$",".")))
      .toList
      .filter(t => es.contains(t._2))
      .map(_._1)
      .toList
  }

  private def build(): Unit = {
    resourceModels.filter(m => m.routeMapping.path != null).foreach {
      resourceModel =>
        resourceModel.linkModel = new LinkModel2(appPath(), resourceModel.routeMapping.path)
        var result = scala.collection.mutable.ListBuffer[LinkModel2]()
        resourceModel.linkModels = result.toList
    }
  }

  private def getType[T](clazz: Class[T]):scala.reflect.runtime.universe.Type = {
    val runtimeMirror =  scala.reflect.runtime.universe.runtimeMirror(clazz.getClassLoader)
    runtimeMirror.classSymbol(clazz).toType
  }

}
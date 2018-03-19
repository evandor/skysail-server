package io.skysail.domain.model

import akka.http.scaladsl.model.Uri
import io.skysail.domain.routes.RouteMappingI
import org.json4s.DefaultFormats
import org.slf4j.LoggerFactory

import scala.reflect.runtime.universe._
/**
  * A ResourceModel uses the provided RouteMapping to determine the associated entity.
  *
  * The HTTP verbs which will be handled depend on the concrete subclass of the ResourceController provided.
  *
  * @param routeMapping todo
  */
case class ResourceModel(routeMapping: RouteMappingI[_,_]) {

  require(routeMapping.resourceClass != null, "A ResourceModel's resource class must not be null")

  private val log = LoggerFactory.getLogger(this.getClass)

  val entityClass: Type = routeMapping.getEntityType()
  var linkModel: LinkModel2 = _
  var linkModels: List[LinkModel2] = List()
  var entityModel = new EntityModel(entityClass, DefaultFormats)

  def linkFor(clsName: String): Option[String] = {
    if (routeMapping.resourceClass.getName == clsName && routeMapping.path != null) Some(routeMapping.path) else None
  }

  def matchPath(url: Uri, appSegment: String): Boolean = {
    val fullPath = s"$appSegment${routeMapping.path}"
    //log info s"matching '${url.path}'-'$fullPath': "+ (url.path == fullPath)
    url.path.toString() == fullPath
  }

  private def printList(list: List[_]) = list.map(v => v).mkString("")

  private def getPathVariables(path: String) =
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map {
        (_.toString().replace("{", "").replace("}", ""))
      }
      .toList

}
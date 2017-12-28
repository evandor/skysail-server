package io.skysail.domain.routes

import akka.http.scaladsl.server.PathMatcher
import io.skysail.domain.SkysailResource._

import scala.reflect.runtime.universe._
import io.skysail.domain.SkysailResource

import scala.reflect.runtime.universe

/**
  * A RouteMapping defines a connection between a path (like "/bookmarks/:id") and a
  * resource class which is supposed to handle calls to this path.
  *
  * Right now, due to some implementation problems, an additional, somewhat redundant
  * pathMatcher has to be provided. This is supposed to change in future implementations.
  *
  * @param path          a string like "/bookmarks/:id", identifying an application's endpoint
  * @param pathMatcher   the same path as an akka route path Matcher
  * @param resourceClass the resource class which is supposed to handle calls to the path.
  * @tparam T
  */
abstract sealed class RouteMappingI[S: TypeTag, T /*<: DddElement*/ : TypeTag](
                                                                                val path: String,
                                                                                val pathMatcher: PathMatcher[S],
                                                                                val resourceClass: Class[_ <: SkysailResource[_, T]]) {

  var classes: Seq[Class[_]] = List()


  def setTypes(classes: Class[_]*) = {
    this.classes = classes
    this
  }

  def getEntityType(): Type = resourceClass.newInstance().getType()

  def getPathMatcherParameterType(): universe.Type = {
    val targs = typeOf[S] match {
      case TypeRef(_, _, args) => args
    }
    typeOf[S]
  }

}

case class RouteMapping[S: TypeTag, T: TypeTag](
                                                 override val path: String,
                                                 override val pathMatcher: PathMatcher[S],
                                                 override val resourceClass: Class[_ <: SkysailResource[_, T]])
  extends RouteMappingI[S, T](path, pathMatcher, resourceClass)

case class ListRouteMapping[S: TypeTag, T: TypeTag](
                                                     override val path: String,
                                                     override val pathMatcher: PathMatcher[S],
                                                     override val resourceClass: Class[_ <: SkysailResource[_, T]])
  extends RouteMappingI[S, T](path, pathMatcher, resourceClass)

case class CreationMapping[S: TypeTag, T: TypeTag](
                                                    override val path: String,
                                                    override val pathMatcher: PathMatcher[S],
                                                    override val resourceClass: Class[_ <: SkysailResource[_, T]])
  extends RouteMappingI[S, T](path, pathMatcher, resourceClass)

case class UpdateMapping[S: TypeTag, T: TypeTag](
                                                  override val path: String,
                                                  override val pathMatcher: PathMatcher[S],
                                                  override val resourceClass: Class[_ <: SkysailResource[_, T]])
  extends RouteMappingI[S, T](path, pathMatcher, resourceClass)

case class EntityMapping[S: TypeTag, T: TypeTag](
                                                  override val path: String,
                                                  override val pathMatcher: PathMatcher[S],
                                                  override val resourceClass: Class[_ <: SkysailResource[_, T]])
  extends RouteMappingI[S, T](path, pathMatcher, resourceClass)
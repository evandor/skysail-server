package io.skysail.domain.routes

import akka.http.scaladsl.server.PathMatcher
import io.skysail.domain.Resource._

import scala.reflect.runtime.universe._
import io.skysail.domain.Resource

/**
  * A RouteMapping defines a connection between a path (like "/bookmarks/:id") and a
  * resource class which is supposed to handle calls to this path.
  *
  * Right now, due to some implementation problems, an additional, somewhat redundant
  * pathMatcher has to be provided. This is supposed to change in future implementations.
  *
  * @param path a string like "/bookmarks/:id", identifying an application's endpoint
  * @param pathMatcher the same path as an akka route path Matcher
  * @param resourceClass the resource class which is supposed to handle calls to the path.
  * @tparam T
  */
case class RouteMapping[T /*<: DddElement*/: TypeTag](
                                 path: String,
                                 pathMatcher: PathMatcher[_],
                                 resourceClass: Class[_ <: Resource[T]]                    ) {

  var classes: Seq[Class[_]] = List()


  def setTypes(classes: Class[_]*) = {
    this.classes = classes
    this
  }

  def getEntityType(): Type = resourceClass.newInstance().getType()

}
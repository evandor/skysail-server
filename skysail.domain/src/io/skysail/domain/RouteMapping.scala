package io.skysail.domain

import akka.http.scaladsl.server.PathMatcher
import io.skysail.domain.Resource._

import scala.reflect.runtime.universe._

case class RouteMapping[T /*<: DddElement*/: TypeTag](
                                 //                      path: String, resourceClass: Class[_ <: Resource[T]]
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
package io.skysail.domain.messages

import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.RequestContext
import io.skysail.domain.app.ApplicationApi

/**
  * This case class captures the command to process a request.
  *
  * This is used in the ControllerActor and is sent as akka message
  * to the ApplicationActor of the related skysail application.
  *
  * @param ctx the akka-provided request context
  * @param resourceClass a skysail-provided resource class
  * @param application reference to the calling application
  * @param urlParameter to be done
  * @param unmatchedPath to be done
  * @param entity the entity of the resource
  */
case class ProcessCommand(
                           ctx: RequestContext,
                           resourceClass: Class[_ <: io.skysail.domain.SkysailResource[_]],
                           application: ApplicationApi,
                           urlParameter: List[String],
                           unmatchedPath: Uri.Path,
                           entity: Any = null)
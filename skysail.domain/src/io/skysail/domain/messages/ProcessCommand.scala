package io.skysail.domain.messages

import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.RequestContext
import io.skysail.domain.app.ApplicationApi

case class ProcessCommand(
    ctx: RequestContext, 
    cls: Class[_ <: io.skysail.domain.Resource[_]],
    application: ApplicationApi,
    urlParameter: List[String], 
    unmatchedPath: Uri.Path, 
    entity: Any = null)
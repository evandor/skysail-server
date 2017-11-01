package io.skysail.domain.messages

import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.RequestContext

case class ProcessCommand(
    ctx: RequestContext, 
    cls: Class[_ <: io.skysail.domain.Resource[_]], 
    urlParameter: List[String], 
    unmatchedPath: Uri.Path, 
    entity: Any = null)
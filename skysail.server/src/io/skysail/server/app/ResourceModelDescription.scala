package io.skysail.server.app

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{IconLink, Link, Linkable}
import io.skysail.domain.model.ApplicationModel

case class ResourceModelDescription(appModel: ApplicationModel, resourceId: String) extends Entity[String] with Linkable {

  override def id: Option[String] = Some(resourceId)

  override val _links: List[Link] = List(
    IconLink("show",   "fas fa-eye",   "/bookmarks/v1/bms/${id}",  "color:#000066", "show entity")
  )

  val entityModel = appModel.resourceModelFor(resourceId).toString

  val entityType = appModel.resourceModelFor(resourceId).get.entityType.toString

  val routeMapping = appModel.resourceModelFor(resourceId).get.routeMapping.toString

  //val typeArgs = appModel.entityModelFor(entityId).get.deriveTypeArgs

//  val fieldsModel = appModel.entityModelFor(entityId).get.fields.map(f => FieldModelDescription(
//    f.name,
//    f.`type`,
//    f.symbol.annotations
//    //f.symbol.isSynthetic,
//    //f.symbol.typeSignature.resultType
//  ))
}

package io.skysail.server.app

import io.skysail.api.ui.{IconLink, Link, Linkable}
import io.skysail.domain.model.ApplicationModel

case class AppModelDescription(appModel: ApplicationModel) extends Linkable {

  override val _links: List[Link] = List(
    IconLink("show",   "fas fa-eye",   "/bookmarks/v1/bms/${id}",  "color:#000066", "show entity")
  )


  val name: String = appModel.name

  val version = if (appModel.apiVersion != null) appModel.apiVersion.toString() else "-"

  val description = appModel.description

  val resources: List[ResModelDescription] = appModel.resourceModels.map(r => {
    val path = r.routeMapping.path
    val resClass = r.routeMapping.resourceClass

    val entityType = r.routeMapping.getEntityType

    ResModelDescription(
      path,
      if (resClass != null) resClass.getName else "-",
      entityType.toString

    )
  }).toList

  val entities = appModel.entities()

}

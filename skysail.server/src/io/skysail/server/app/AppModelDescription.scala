package io.skysail.server.app

import io.skysail.domain.model.ApplicationModel

case class AppModelDescription(appModel: ApplicationModel) {

  val name: String = appModel.name

  val version = if (appModel.apiVersion != null) appModel.apiVersion.toString() else "-"

  val description = appModel.description

  val resources: List[ResModelDescription] = appModel.resourceModels.map(r => {
    val path = r.routeMapping.path
    val resClass = r.routeMapping.resourceClass

    val entityType = r.routeMapping.getEntityType

    ResModelDescription(
      path,
      if (resClass != null) resClass.getSimpleName else "-",
      if (resClass != null) "https://github.com/evandor/skysail-server/blob/release/0.0/skysail.server.demo/src/" + resClass.toString.replace(".", "/") + ".scala" else "-",
      entityType.toString

    )
  }).toList

  val entities = appModel.entities()

}

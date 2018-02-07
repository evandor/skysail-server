package io.skysail.server.app

import io.skysail.domain.model.ApplicationModel

case class AppModelDescription(appModel: ApplicationModel) {

  val name = appModel.name

  val version = appModel.apiVersion.toString()

  val description = appModel.description

  val resources: List[ResModelDescription] = appModel.resourceModels.map(r => {
      val path = r.routeMapping.path
      val resClass = r.routeMapping.resourceClass
      ResModelDescription(
        path,
        if (resClass != null) resClass.getSimpleName else "-"
      )
    }).toList

}

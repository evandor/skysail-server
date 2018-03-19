package io.skysail.server.app

import io.skysail.domain.model.ApplicationModel

case class EntityModelDescription(appModel: ApplicationModel, entityId: String) {

  val entityModel = appModel.entityModelFor(entityId).toString
  
  //val typeArgs = appModel.entityModelFor(entityId).get.deriveTypeArgs

  val fieldsModel = appModel.entityModelFor(entityId).get.fields.map(f => FieldModelDescription(
    f.name,
    f.`type`,
    f.symbol.annotations
    //f.symbol.isSynthetic,
    //f.symbol.typeSignature.resultType
  ))


}

package io.skysail.domain.model

object EntityDescription {
  def apply(model: EntityModel) = {
    val fields = model.fields
    new EntityDescription(model.fields.map(m => FieldDescription(m)).toList)
  }
}

case class EntityDescription(fields: List[FieldDescription]) {}
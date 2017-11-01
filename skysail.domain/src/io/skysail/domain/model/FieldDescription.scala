package io.skysail.domain.model

object FieldDescription {
  def apply(model: MemberModel) = {
    new FieldDescription(model.name, "xxx"/*model.`type`*/)
  }
}

class FieldDescription(val name: String, val `type`: String) {
  
}
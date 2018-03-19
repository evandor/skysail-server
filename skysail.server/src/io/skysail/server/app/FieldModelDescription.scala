package io.skysail.server.app

case class FieldModelDescription(
                                  name: String,
                                  `type`: String,
                                  annotations: List[reflect.runtime.universe.Annotation]
                                ) {



}

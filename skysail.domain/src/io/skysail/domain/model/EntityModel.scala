package io.skysail.domain.model

import org.json4s.Formats

import scala.reflect.runtime.universe._

case class EntityModel(entityType: Type, dfs: Formats) {

  require(entityType != null, "The provided entity class must not be null")

  def name() = entityType.toString

  lazy val fields: List[MemberModel] = deriveFields()

  def description() = EntityDescription(this)

  //val runtimeMirror =  scala.reflect.runtime.universe.runtimeMirror()


  // def fieldFor(id: String): Option[FieldModel] = fields.get(id)

  /*entityType.members.filter(!_.isMethod).map(_.typeSignature).foreach {
    case t => print("hier: " + t + "("+t.getClass+")")
  }*/

  //private def deriveFields() = entityClass.members.collect { case m: MethodSymbol if m.isGetter => MemberModel(m) }.toList
  private def deriveFields() = {
    //println(entityType.as)
    entityType.members
      .filter(!_.isMethod)
      //.map(_.typeSignature)
      .collect { case m => MemberModel(m) }
      .toList
  }

  //private def filterFormFields(f: Field): Boolean = f.getAnnotation(classOf[io.skysail.core.html.Field]) != null

}
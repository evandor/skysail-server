package io.skysail.domain.model

import org.json4s.Formats

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._

case class EntityModel(entityType: Type, dfs: Formats) {

  require(entityType != null, "The provided entity class must not be null")

  def name() = entityType.toString

  val fields: List[MemberModel] = deriveFields()

  def description() = EntityDescription(this)

  //val runtimeMirror =  scala.reflect.runtime.universe.runtimeMirror()

//  entityType
//  val c: universe.ClassSymbol = entityType.typeSymbol.asClass;
  println(listProperties)
  //  val members = deriveMembers()
  //  val decls = deriveDeclarations()

  // def fieldFor(id: String): Option[FieldModel] = fields.get(id)

  /*entityType.members.filter(!_.isMethod).map(_.typeSignature).foreach {
    case t => print("hier: " + t + "("+t.getClass+")")
  }*/

  //private def deriveFields() = entityClass.members.collect { case m: MethodSymbol if m.isGetter => MemberModel(m) }.toList
  private def deriveFields() = entityType.members
    .filter(!_.isMethod)
    //.map(_.typeSignature)
    .collect { case m => MemberModel(m) }
    .toList

  // https://stackoverflow.com/questions/17792383/how-to-list-all-fields-with-a-custom-annotation-using-scalas-reflection-at-runt
  def listProperties/*: List[(TermSymbol, Annotation)]*/ = {
    // a field is a Term that is a Var or a Val
    /*val fields = */
    entityType.members.collect{ case s: TermSymbol => s }.
      filter(s => s.isVal || s.isVar)

    // then only keep the ones with a MyProperty annotation
    //fields.flatMap(f => f.annotations.find(_.tpe =:= typeOf[MyProperty]).
    //  map((f, _))).toList
  }

  //val deriveTypeArgs = entityClass.typeArgs.toString()
  
  
  //private def deriveMembers() = entityClass.members.map(m => MemberModel(m)).toList
  //private def deriveDeclarations() = entityClass.decls.map(m => MemberModel(m)).toList

//  def toHtml() = s"""${this.getClass.getSimpleName}("$name")<br>
//        <br><u>Fields</u>: <ul>${printHtmlMap(fields)}</ul><br>"""
//  
//  override def toString() = s"""${this.getClass.getSimpleName}("$name")
//        Fields: ${printMap(fields)}"""
//  
  //private def filterFormFields(f: Field): Boolean = f.getAnnotation(classOf[io.skysail.core.html.Field]) != null

}
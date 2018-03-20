package io.skysail.server.demo.domain

import io.skysail.domain.model.MemberModel
import org.junit.Test

import scala.reflect.runtime.universe._

class Comment1Test  {

//  @Test
//  def testScalaStaticClassAnnotation() {
//    import scala.reflect.runtime.universe._
//    val a = typeOf[Comment1].typeSymbol.annotations.head
//    assertTrue(a.toString == "SerialVersionUID(value = 1L)")
//  }

  @Test
  def test(): Unit = {
    val l = typeOf[Comment1].members
      .filter(!_.isMethod)
      //.map(_.typeSignature)
      .collect { case m => MemberModel(m) }
      .toList
    l.foreach(println(_))
  }

  @Test
  def test3(): Unit = {
    import scala.reflect.runtime.universe._
    println(symbolOf[Comment1])
    println(symbolOf[Comment1].asClass)
    println(symbolOf[Comment1].asClass.primaryConstructor)
    println(symbolOf[Comment1].asClass.primaryConstructor.typeSignature)
    println(symbolOf[Comment1].asClass.primaryConstructor.typeSignature.paramLists)
    println(symbolOf[Comment1].asClass.primaryConstructor.typeSignature.paramLists.head)
    val t = symbolOf[Comment1].asClass.primaryConstructor.typeSignature.paramLists.head.map(_.annotations)
    println("Comment1: " + t)
  }
}

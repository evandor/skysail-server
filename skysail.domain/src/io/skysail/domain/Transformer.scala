package io.skysail.domain

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.read
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import scala.reflect.runtime.universe._
import scala.util.{ Failure, Success }
import org.json4s.JsonAST.JValue
import scala.reflect.ClassTag
import scala.reflect.ManifestFactory

object Transformer {

  implicit val formats = DefaultFormats
  //implicit val formats = DefaultFormats + ZDTSerializer + FieldSerializer[Person]()
  implicit val serialization: Serialization.type = jackson.Serialization

  def jsonStringToBean[T: Manifest](jsonStr: String): T = {
    //    val ast = parse(jsonStr)
    //    ast.extract[T]
    read[T](jsonStr)
  }

  def beanToJson[T: Manifest](bean: T): JValue = {
    
    implicit val serialization: Serialization.type = jackson.Serialization
    implicit val formats = DefaultFormats + FieldSerializer[T]()
    println(bean)
    //val john = Person("john", ZonedDateTime.now())

    
    val e1 = Extraction.decompose(bean)

    //val written = write(bean)
    println("e1"+e1)
    e1
  }

  def toManifest[T:TypeTag]: Manifest[T] = {
    val t = typeTag[T]
    val mirror = t.mirror
    def toManifestRec(t: Type): Manifest[_] = {
      val clazz = ClassTag[T](mirror.runtimeClass(t)).runtimeClass
      if (t.typeArgs.length == 1) {
        val arg = toManifestRec(t.typeArgs.head)
        ManifestFactory.classType(clazz, arg)
      } else if (t.typeArgs.length > 1) {
        val args = t.typeArgs.map(x => toManifestRec(x))
        ManifestFactory.classType(clazz, args.head, args.tail: _*)
      } else {
        ManifestFactory.classType(clazz)
      }
    }
    toManifestRec(t.tpe).asInstanceOf[Manifest[T]]
  }
}

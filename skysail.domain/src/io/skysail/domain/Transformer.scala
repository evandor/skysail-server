package io.skysail.domain

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.read
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

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

}

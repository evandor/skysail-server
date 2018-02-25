package io.skysail.domain

import io.skysail.domain.model.ApplicationModel
import org.json4s.JsonAST.JValue
import org.json4s._
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.read

import scala.reflect.{ClassTag, ManifestFactory}
import scala.reflect.runtime.universe._

object Transformer {

  implicit val formats = DefaultFormats
  //implicit val formats = DefaultFormats + ZDTSerializer + FieldSerializer[Person]()
  implicit val serialization: Serialization.type = jackson.Serialization

  // TODO check https://stackoverflow.com/questions/15943957/is-it-possible-to-make-json4s-not-to-throw-exception-when-required-field-is-miss
  def jsonStringToBean[T: Manifest](jsonStr: String): T = {
    println("JSON: " + jsonStr)
    val r = org.json4s.native.JsonMethods.parse(jsonStr).extractOpt[T]
    println("RRR:" + r)
    val r2 = read[T](jsonStr)
    println("RRR2:" + r2)
    r2
  }

  def jsonStringToBean2[T: Manifest](json: String, cls: Class[T]): T = {
    println(json)
    val ast = parse(json)
    println(ast)
    implicit val formats = DefaultFormats

    val from = (ast \\ "out_from" \ "in")(0)
    val to = (ast \\ "out_to" \ "in")(0)
    val p = ast transformField {
      case JField("out_from", JArray(s)) => ("from", from)
      case JField("out_to", JArray(s)) => ("to", to)
    }

    println("AST2" + p)
    p.extract[T]
  }

  def jsonStringToBean3[T: Manifest](json: String, cls: Class[T], appModel: ApplicationModel): T = {
    println(json)
    val ast = parse(json)
    println(ast)
    implicit val formats = DefaultFormats

    val edgeFields: Seq[String] = appModel.entityRelationFields(cls)
    //println(edgeFields)
    val edgeFieldNameJValuesPairs: Seq[(String, String, JValue)] = edgeFields.map(f =>(f, s"out_$f", (ast \\ s"out_$f" \ "in")(0)))

    var l = ast
    for(p <- edgeFieldNameJValuesPairs) {
      l = l transformField {
        case JField(p._2, JArray(s)) => (p._1, p._3)
      }
    }

    println("AST2" + l)
    l.extract[T]

  }

  def jsonStringToBeanWithTemplate[T: Manifest](jsonStr: String, template: T): T = {
    val defaultsJson = Extraction.decompose(template)
    val valueJson = JsonUtil.jValue(jsonStr)
    (defaultsJson merge valueJson).extract[T]
  }

  def beanToJson[T: Manifest](bean: T): JValue = {
    implicit val serialization: Serialization.type = jackson.Serialization
    implicit val formats = DefaultFormats + FieldSerializer[T]()
    Extraction.decompose(bean)
  }

  def listToJson[T: Manifest](bean: List[T]): JValue = {

    implicit val serialization: Serialization.type = jackson.Serialization
    implicit val formats = DefaultFormats + FieldSerializer[T]()
    Extraction.decompose(bean)
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

  def toClassTag[T: TypeTag]: ClassTag[T] = {
    ClassTag[T]( typeTag[T].mirror.runtimeClass( typeTag[T].tpe ) )
  }

  object JsonUtil {
    import java.nio.charset.StandardCharsets.UTF_8
    import java.io.{InputStreamReader, ByteArrayInputStream}

    def jValue(json: String): JValue = {
      jValue(json.getBytes(UTF_8))
    }

    def jValue(json: Array[Byte]): JValue = {
      val reader = new InputStreamReader(new ByteArrayInputStream(json), UTF_8)
      native.JsonParser.parse(reader)
    }
  }
}

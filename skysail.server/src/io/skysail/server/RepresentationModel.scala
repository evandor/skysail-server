package io.skysail.server

import akka.http.scaladsl.model.Uri
import com.fasterxml.jackson.databind.ObjectMapper
import io.skysail.domain.ResponseEventBase
import io.skysail.domain.model.{ApplicationModel, EntityModel}
import org.json4s.JsonAST.JArray
import org.json4s.jackson.JsonMethods._
import org.json4s.{DefaultFormats, Extraction, JObject, JValue, jackson}
import org.slf4j.LoggerFactory
import io.gatling.jsonpath.JsonPath

class RepresentationModel(
                           val response: ResponseEventBase,
                           val model: ApplicationModel) {

  private val log = LoggerFactory.getLogger(this.getClass)

  private val mapper = new ObjectMapper

  //val entity: T = response.entity.asInstanceOf[T]

  val rawData: List[Map[String, Any]] = deriveRawData()

  //val t: Option[Any] = rawData.head.get("accounts")

  val jsonData: String = deriveJsonData()

  val json: JValue = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    Extraction.decompose(response.entity)
  }

  //json.asInstanceOf[JObject].values.get("accounts")

  def entityModel(): Option[EntityModel] = {
    val uri: Uri = response.req.uri
    model.entityModelFor(uri)
  }

  def linkFor(clsName: String, id: Option[Any]): String = {
    val link: Option[String] = model.linkFor(clsName)
    link.getOrElse("").replace(":id", id.getOrElse("").toString)
  }
  
  def getString(path:String): String = {
    println("XXX"+path)
    println("XXX"+json)
    val a = JsonPath.query(path, json)
    println(a)
    val b = a.right.get.next()
    println(b)

    b  .toString()
  }

  private def deriveRawData(): List[Map[String, Any]] = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    //val r = responseEvent.resource
    val e = Extraction.decompose(response.entity)
    //.asInstanceOf[JArray]
    val res = e match {
      case _: JArray => {
        e.children.map(c => {
          //println("c: " + c)
          val c2 = c.asInstanceOf[JObject]
          val vals = c2.values
          vals
        })
      }
      case o: JObject => {
        //e.children.map(c => {
        //println("c: " + c)
        List(o.values)
        //})
      }
      case a: Any =>
        log info s"$a"
        Nil

    }
    res
  }

  private def deriveJsonData(): String = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    //val r = responseEvent.resource
    val e: JValue = Extraction.decompose(response.entity)
    compact(render(e))
  }
}

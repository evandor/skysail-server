package io.skysail.server

import com.fasterxml.jackson.databind.ObjectMapper
import org.json4s.JsonAST.JArray
import org.json4s.{DefaultFormats, Extraction, JObject, JValue, jackson}
import org.slf4j.LoggerFactory
import io.skysail.domain.ResponseEventBase
import io.skysail.domain.model.ApplicationModel
import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

class RepresentationModel(/*responseEvent: ListResponseEvent[_]*/
                          response: ResponseEventBase,
                          model: ApplicationModel) {

  private val log = LoggerFactory.getLogger(this.getClass)

  private val mapper = new ObjectMapper

  //private val model = response.resource
  val rawData: List[Map[String, Any]] = deriveRawData()

  val jsonData: String = deriveJsonData()

  def linkFor(clsName: String, id: Option[Any]): String = {
    val link: Option[String] = model.linkFor(clsName)
    link.getOrElse("").replace(":id", id.getOrElse("").toString)
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

    print("RES: " + res)
    res

    /*if (r.isInstanceOf[List[_]]) {
      println("R: " + r)
      r.asInstanceOf[List[_]].map(row => {
        //val s = mapper.convertValue(row, classOf[util.LinkedHashMap[String, Any]])

        //      val written = write(e)

        println("row: " + s)
        s
      }).toList
    } else {
      Nil
    }*/


  }

  private def deriveJsonData(): String = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    //val r = responseEvent.resource
    val e: JValue = Extraction.decompose(response.entity)
    compact(render(e))
  }
}

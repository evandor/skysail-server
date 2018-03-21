package io.skysail.server

import com.fasterxml.jackson.databind.ObjectMapper
import io.gatling.jsonpath.JsonPath
import io.skysail.api.ui.{ButtonLink, Link, Linkable, ListPayload}
import io.skysail.domain.ResponseEventBase
import io.skysail.domain.model.{ApplicationModel, EntityModel, ResourceModel}
import org.json4s.JsonAST.JArray
import org.json4s.jackson.JsonMethods._
import org.json4s.{DefaultFormats, Extraction, JObject, JValue, jackson}
import org.slf4j.LoggerFactory

class RepresentationModel(
                           val response: ResponseEventBase,
                           val model: ApplicationModel) {

  private val log = LoggerFactory.getLogger(this.getClass)

  private val mapper = new ObjectMapper

  val rawData: List[Map[String, Any]] = deriveRawData()

  val jsonData: String = deriveJsonData()

  val jsonObject: Any = {
    mapper.readValue(jsonData, classOf[Object])
  }

  val json: JValue = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    Extraction.decompose(response.entity)
  }

  val links = {
    val e = response.entity
    if (e.isInstanceOf[Linkable]) {
      e.asInstanceOf[Linkable]._links
    } else {
      implicit object WrapperCanFoo extends Linkable {
        override def _links: Seq[Link] = {
          List(
            ButtonLink("create-form", "create new Entity", "/demo/v1/bms/", style = "btn btn-outline-primary")
          )
        }
      }
      def foo[_](thing: Any)(implicit evidence: Linkable) = evidence._links
      // def foo[A:CanFoo](thing: A) = implicitly[CanFoo[A]].foos(thing)
      foo(e)
    }
  }

  def entityModel(): Option[EntityModel] = {
    val resClass = response.req.cmd.mapping.resourceClass
    val r: Option[ResourceModel] = model.resourceModelFor(resClass.getName)
    r.map(_.entityModel)
  }

  def linkFor(clsName: String, id: Option[Any]): String = {
    val link: Option[String] = model.linkFor(clsName)
    link.getOrElse("").replace(":id", id.getOrElse("").toString)
  }

  def getString(path: String): String = {
    val queryResult = JsonPath.query("$" + path, jsonObject)
    queryResult.fold(
      ex => "Operation failed with " + ex,
      v => if (v.hasNext) v.next().toString else "*!*")
  }

  def itemsOf(path: String): Iterator[Any] = {
    val queryResult = JsonPath.query("$" + path, jsonObject)
    queryResult.fold(
      ex => List[Any]().iterator,
      v => v
    )
  }

  private def deriveRawData(): List[Map[String, Any]] = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    //val r = responseEvent.resource
    val payload = if (response.entity.isInstanceOf[ListPayload[_]]) {
      response.entity.asInstanceOf[ListPayload[_]].payload
    } else {
      response.entity
    }
    val e = Extraction.decompose(payload)
    //.asInstanceOf[JArray]
    val res = e match {
      case _: JArray => {
        e.children.map(c => {
          val c2 = c.asInstanceOf[JObject]
          val vals = c2.values
          vals
        })
      }
      case o: JObject => {
        List(o.values)
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
    val e: JValue = Extraction.decompose(response.entity)
    compact(render(e))
  }
}

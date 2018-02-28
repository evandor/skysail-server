package io.skysail.domain

import io.skysail.domain.app.ApiVersion
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.testdomains._
import org.json4s
import org.json4s.{DefaultFormats, jackson}
import org.junit.Test
import org.json4s.jackson.JsonMethods._
import org.json4s.ext.EnumNameSerializer

class TransformerTest {
  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

  val root = HttpResource("rootid", Set())
  val rootOrientDb = HttpResourceOrientDb("rootid", Set("#103:0"))
  val appModel = ApplicationModel("testapp", ApiVersion(1), "test")

  appModel.addEntity(classOf[Bookmark])
  appModel.addEntity(classOf[HttpResource])

//  @Test
//  def accountToJson(): Unit = {
//    val bm = Bookmark(Some("from_account"), "title", "url", root = root)
//    val json: json4s.JValue = Transformer.beanToJson(bm)
//    println(json)
//    println(compact(render(json)))
//
//  }

  @Test
  def accountToJson(): Unit = {
    val edge = OutEdge("#100:1", rootOrientDb)
    val bm = BookmarkOrientDb(Some("bmId"), "title", "url", out_root = List(edge))
    val json: json4s.JValue = Transformer.beanToJson(bm)
    println(json)
    val jsonString: String = compact(render(json))
    println(jsonString)
    val r = Transformer.jsonStringToBean3(jsonString, classOf[Bookmark], appModel)
    println(r)
    assert(r.title == "title")
    assert(r.root.id == "rootid")
  }

  @Test
  def accountToJson2(): Unit = {
    import org.json4s.{DefaultFormats, FieldSerializer}

    val bm = Bookmark(Some("bmId"), "title", "url")
    val f = DefaultFormats + FieldSerializer[Bookmark]() + new EnumNameSerializer(io.skysail.domain.testdomains.State)
    val json: json4s.JValue = Transformer.beanToJson2(bm,f)
    println(json)
    val jsonString: String = compact(render(json))
    println(jsonString)
  }


}

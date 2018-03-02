package io.skysail.domain

import io.skysail.domain.app.ApiVersion
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.testdomains._
import org.json4s
import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.JsonMethods._
import org.json4s.{DefaultFormats, FieldSerializer, JValue, jackson}
import org.junit.Test
import org.assertj.core.api.Assertions._


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
  def bookmark2bean(): Unit = {
    val jsonString: String = createOrientDbJsonForTestBookmark("title", "url")
    val r = Transformer.jsonStringToBean3(jsonString, classOf[Bookmark], appModel)
    assert(r.title == "title")
    assert(r.url == "url")
    assert(r.state == State.NEW)
    assert(r.root.id == "rootid")
  }

  @Test
  def stateOnly2Bean(): Unit = {
    val jsonString: String = "{\"state\":\"Unknown\"}"
    implicit val formats = DefaultFormats + FieldSerializer[Bookmark]() + new EnumNameSerializer(State)
    //val implicit formats = DefaultFormats + new EnumNameSerializer(StateOnly)
    val r = Transformer.jsonStringToBean3(jsonString, classOf[StateOnly], appModel)
    assertThat(r.state).isEqualTo(State.Unknown)
  }

  @Test
  def accountToJson2(): Unit = {
    import org.json4s.{DefaultFormats, FieldSerializer}

    val bm = Bookmark(Some("bmId"), "title", "url")
    val f = DefaultFormats + FieldSerializer[Bookmark]() + new EnumNameSerializer(State)
    val json: json4s.JValue = Transformer.beanToJson2(bm,f)
    println(json)
    val jsonString: String = compact(render(json))
    println(jsonString)
  }

  private def createOrientDbJsonForTestBookmark(title: String, url: String): String = {
    val edge = OutEdge("#100:1", rootOrientDb)
    val bm = BookmarkOrientDb(Some("bmId"), title, url, out_root = List(edge))
    val json: JValue = Transformer.beanToJson(bm)
    println(json)
    val jsonString: String = compact(render(json))
    println(jsonString)
    jsonString
  }

}

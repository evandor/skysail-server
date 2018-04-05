package io.skysail.server.ui

import io.skysail.api.ui.ImmutableMenuItem
import org.json4s.{CustomSerializer, DefaultFormats, Extraction, FieldSerializer, JObject, JsonAST, _}
import org.json4s.JsonDSL._

class MenuItemSerializer extends CustomSerializer[ImmutableMenuItem](format => ( {

  case jsonObj: JObject =>
    implicit val formats = DefaultFormats

    val name = (jsonObj \ "name").extract[String]
    val url = (jsonObj \ "url").extract[String]

    ImmutableMenuItem(name, url)
}, {
  case menuItem: ImmutableMenuItem =>
    ("name" -> menuItem.name) ~
      ("url" -> menuItem.url) ~
      ("id" -> menuItem.id) ~
      ("children" -> menuItem.children.map {c:ImmutableMenuItem =>
        Extraction.decompose(c)(DefaultFormats + FieldSerializer[ImmutableMenuItem]())
      })
   //case menuItem: MenuItem => Extraction.decompose(menuItem)(DefaultFormats + FieldSerializer[MenuItem]())
}
))
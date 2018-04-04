package io.skysail.server.ui

import io.skysail.api.ui.MenuItem
import org.json4s.{CustomSerializer, DefaultFormats, Extraction, FieldSerializer, JObject, JsonAST, _}
import org.json4s.JsonDSL._

class MenuItemSerializer extends CustomSerializer[MenuItem](format => ( {

  case jsonObj: JObject =>
    implicit val formats = DefaultFormats

    val name = (jsonObj \ "name").extract[String]
    val url = (jsonObj \ "url").extract[String]

    MenuItem(name, url)
}, {
  case menuItem: MenuItem =>
    ("name" -> menuItem.name) ~
      ("url" -> menuItem.url) ~
      ("id" -> menuItem.id) ~
      ("children" -> menuItem.children.map {c:MenuItem =>
        Extraction.decompose(c)(DefaultFormats + FieldSerializer[MenuItem]())
      })
   //case menuItem: MenuItem => Extraction.decompose(menuItem)(DefaultFormats + FieldSerializer[MenuItem]())
}
))
package io.skysail.server.ui

import io.skysail.api.ui.MenuItem
import org.json4s.{CustomSerializer, DefaultFormats, Extraction, FieldSerializer, JObject}

class MenuItemSerializer extends CustomSerializer[MenuItem](format => ( {
  case jsonObj: JObject =>
    implicit val formats = DefaultFormats

    val name = (jsonObj \ "name").extract[String]
    val url = (jsonObj \ "url").extract[String]

    MenuItem(name, url)
}, {
//  case menuItem: MenuItem =>
//    ("name" -> menuItem.name) ~
//      ("url" -> menuItem.url) ~
//      ("id" -> 7) ~
//      ("children" -> )
  case menuItem: MenuItem => Extraction.decompose(menuItem)(DefaultFormats + FieldSerializer[MenuItem]())
}
))
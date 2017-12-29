package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable}

case class Bookmark(id: Option[String], title: String, url: String) extends Entity[String] with Linkable {
  override def _links = List(Link("self", "hier"))
}

class BookmarkWithVariants(id: String, title: String, url: String, val hits: Seq[(String, List[String])])
  extends Bookmark(Some(id), title, url) {

//  val variants:String = {
//    val lists = hits.map(hit => hit._2.map(sub => url.replace("${" + hit._1 + "}", sub))).flatten.toList
//    println("Lists: " + lists)
//    lists.toString()
//  }

  val variants = "hi"
}

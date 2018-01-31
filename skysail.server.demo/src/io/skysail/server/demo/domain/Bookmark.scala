package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable}

//object Bookmark {
//  implicit val colorFormat = jsonFormat1(Bookmark)
//}

case class Bookmark(
                     id: Option[String],
                     title: String, url: String,
                     variants: List[Bookmark] = List()) extends Entity[String] with Linkable {

  override def _links = List(Link("self", "hier"))

  // see RootJsonFormat
  // https://github.com/spray/spray-json
  //@JsonProperty
  //val variants1 = "xxx"

}

case class BookmarkList(
                         bookmarks: List[Bookmark]
                       )

//class BookmarkWithVariants(id: String, title: String, url: String, val hits: Seq[(String, List[String])])
//  extends Bookmark(Some(id), title, url) {
//
////  val variants:String = {
////    val lists = hits.map(hit => hit._2.map(sub => url.replace("${" + hit._1 + "}", sub))).flatten.toList
////    println("Lists: " + lists)
////    lists.toString()
////  }
//
//  @JsonProperty
//  val variants = "hi"
//}

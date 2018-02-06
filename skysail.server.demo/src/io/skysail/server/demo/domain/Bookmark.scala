package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui._

case class Bookmark(
                     id: Option[String],
                     title: String,
                     url: String
                   ) extends Entity[String] with Linkable {

  override val _links: List[Link] = List(
    IconLink("show",   "fas fa-eye",   "/demo/v1/bms/${id}",  "color:#000066", "show entity"),
    IconLink("update", "fa fa-edit",   "/demo/v1/bms/${id}/", "", "update entity"),
    IconLink("delete", "fas fa-trash", "/demo/v1/bms/${id}/", "color:Tomato", "delete entity")
  )

}


case class BookmarkList(
                         bookmarks: List[Bookmark]
                       ) extends Linkable {

  override val _links: List[Link] = List(
    ButtonLink("create-form", "create new Bookmark", "/demo/v1/bms/", style = "btn btn-outline-primary")
  )
}

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

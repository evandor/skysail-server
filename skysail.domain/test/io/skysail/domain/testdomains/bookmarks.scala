package io.skysail.domain.testdomains

import java.time.Instant

object State {

  sealed trait EnumVal

  case object NEW extends EnumVal

  case object NOT_FOUND extends EnumVal

  case object UNCHANGED extends EnumVal

  case object CHANGED extends EnumVal

}

case class BookmarkOrientDb(
                     id: Option[String],
                     title: String,
                     url: String,
                     favIcon: Option[String] = Some("http://www.spiegel.de/favicon.ico"),
                     hash: String = "",
                     created: Long = Instant.MIN.getEpochSecond,
                     clicked: Integer = 0,
                     out_root: List[OutEdge],
                     state: Option[State.EnumVal] = Some(State.NEW)
                   )


case class Bookmark(
                     id: Option[String],
                     title: String,
                     url: String,
                     favIcon: Option[String] = Some("http://www.spiegel.de/favicon.ico"),
                     hash: String = "",
                     created: Long = Instant.MIN.getEpochSecond,
                     clicked: Integer = 0,
                     root: HttpResource = null,
                     state: Option[State.EnumVal] = Some(State.NEW)
                   )


case class BookmarkList(bookmarks: List[Bookmark])

case class HttpResource(
                         id: String, // = url
                         links: Set[HttpResource] = Set()
                       )

case class HttpResourceOrientDb(
                         id: String, // = url
                         in_root: Set[String] = Set()
                       )

case class OutEdge(
                  out: String,
                  in: HttpResourceOrientDb
                  )

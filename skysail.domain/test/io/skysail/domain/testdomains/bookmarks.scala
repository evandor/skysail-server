package io.skysail.domain.testdomains

import java.time.Instant

//object State
//sealed abstract class State
//case object NEW extends State
//case object NOT_FOUND extends State
//case object UNCHANGED extends State
//case object CHANGED extends State


case class BookmarkOrientDb(
                             id: Option[String],
                             title: String,
                             url: String,
                             favIcon: Option[String] = Some("http://www.spiegel.de/favicon.ico"),
                             hash: String = "",
                             created: Long = Instant.MIN.getEpochSecond,
                             clicked: Integer = 0,
                             out_root: List[OutEdge],
                             state: State.Value = State.NEW
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
                     state: State.Value = State.NEW
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

case class StateOnly(
                      state: State.Value = State.NEW
                    )
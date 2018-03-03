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
                             state: ScalaState.Value = ScalaState.NEW
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
                     state: ScalaState.Value = ScalaState.NEW
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
                      state: ScalaState.Value = ScalaState.NEW
                    )

case class JavaStateOnly(
                      state: JavaState = JavaState.JavaDefault
                    )
package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui._

object MonitorState {
  sealed trait EnumVal
  case object NEW extends EnumVal
  case object NOT_FOUND extends EnumVal
  case object UNCHANGED extends EnumVal
  case object CHANGED extends EnumVal
}

case class Monitor(
                     id: Option[String],
                     title: String,
                     url: String,
                     state: Option[MonitorState.EnumVal] = Some(MonitorState.NEW)
                   ) extends Entity[String] with Linkable {

  override val _links: List[Link] = List(
  )

}


case class MonitorList(
                         monitors: List[Monitor]
                       ) extends Linkable {

  override val _links: List[Link] = List(
  )
}

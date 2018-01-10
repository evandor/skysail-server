package io.skysail.api.health

object Status {
  val UNKNOWN = Status("UNKNOWN")
  val UP = Status("UP")
  val DOWN = Status("DOWN")
  val OUT_OF_SERVICE = Status("OUT_OF_SERVICE")
}

case class Status(value: String) extends AnyVal
package io.skysail.domain.app

case class ApiVersion(versionNr: Int) {
  require(versionNr > 0, "version number must be greater zero")
  override def toString(): String = "v" + versionNr
}
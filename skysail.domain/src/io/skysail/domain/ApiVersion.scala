package io.skysail.domain

case class ApiVersion(versionNr: Int) {
  require(versionNr > 0, "version number must be greater zero")
  override def toString() = "v" + versionNr
}
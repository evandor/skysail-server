package io.skysail.domain.app

/**
  * A skysail application can have a version associated to it.
  *
  * The version number is an integer, its string representation has a "v"-prefix.
  * Typically, the application name and its version make up the root path of any
  * endpoints provided by the application, e.g. "https://thehost/crm/v1/...".
  *
  * The goal of any API designer should be to _not_ have to introduce new versions
  * of the API.
  *
  * If an appliation does not have a version, its endpoints would look like
  * "https://thehost/crm/...".
  *
  * @param versionNr a positive integer, potentially increasing over time.
  */
case class ApiVersion(versionNr: Int) {
  require(versionNr > 0, "version number must be greater zero")
  override def toString(): String = "v" + versionNr
}
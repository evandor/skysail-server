package io.skysail.api.config

trait PropertyProvider {
  def get(key: String): String
  def getList(key: String): List[String]
}
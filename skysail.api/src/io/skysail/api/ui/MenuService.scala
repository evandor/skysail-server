package io.skysail.api.ui

trait MenuService {
  var root: MenuItem
  def register(parentPath: String, item: MenuItem): MenuItem
  def find(path: String): Option[MenuItem]
}

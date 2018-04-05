package io.skysail.api.ui

trait ImmutableMenuService {
  var root: ImmutableMenuItem
  def register(parentPath: String, item: ImmutableMenuItem): ImmutableMenuItem
  def find(path: String): Option[ImmutableMenuItem]
}

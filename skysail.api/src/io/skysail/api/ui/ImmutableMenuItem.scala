package io.skysail.api.ui

case class ImmutableMenuItem(name: String, url: String, children: List[ImmutableMenuItem] = List()) {

  var id = 0

  require(name != null, "the id of a MenuItem must not null")
  require(!name.exists(c => '/'.equals(c)), "the id must not contain any slashes")
  require(!name.exists(c => '\\'.equals(c)), "the id must not contain any slashes")
  require(!name.exists(_.isWhitespace), "the id must not contain any whitespace characters")

  def addItem(item: ImmutableMenuItem): ImmutableMenuItem = {
    copy(children = children :+ item)
  }

  def update(changedItem: ImmutableMenuItem): ImmutableMenuItem = {
    copy(children = children.map(i => if (i.name == changedItem.name) changedItem else i))
  }

}

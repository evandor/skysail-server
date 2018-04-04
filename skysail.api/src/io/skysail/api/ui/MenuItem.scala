package io.skysail.api.ui

case class MenuItem(name: String, url: String, children: List[MenuItem] = List()) {

  require(name != null, "the id of a MenuItem must not null")
  require(!name.exists(c => '/'.equals(c)), "the id must not contain any slashes")
  require(!name.exists(c => '\\'.equals(c)), "the id must not contain any slashes")
  require(!name.exists(_.isWhitespace), "the id must not contain any whitespace characters")

  def addItem(item: MenuItem): MenuItem = {
    copy(children = children :+ item)
  }

  def update(changedItem: MenuItem): MenuItem = {
    copy(children = children.map(i => if (i.name == changedItem.name) changedItem else i))
  }

}

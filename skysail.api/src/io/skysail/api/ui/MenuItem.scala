package io.skysail.api.ui

case class MenuItem(text: String, url: String, nodes: List[MenuItem] = List()) {

  require(text != null, "the id of a MenuItem must not null")
  require(!text.exists(c => '/'.equals(c)), "the id must not contain any slashes")
  require(!text.exists(c => '\\'.equals(c)), "the id must not contain any slashes")
  require(!text.exists(_.isWhitespace), "the id must not contain any whitespace characters")

  def addItem(item: MenuItem): MenuItem = {
    copy(nodes = nodes :+ item)
  }

  def update(changedItem: MenuItem): MenuItem = {
    copy(nodes = nodes.map(i => if (i.text == changedItem.text) changedItem else i))
  }

}

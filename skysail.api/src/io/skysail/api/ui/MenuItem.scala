package io.skysail.api.ui

class MenuItem(
                var name: String,
                var url: String,
                var children: List[MenuItem] = List()) {

  var id = 1

  require(name != null, "the id of a MenuItem must not null")
  require(!name.exists(c => '/'.equals(c)), "the id must not contain any slashes")
  require(!name.exists(c => '\\'.equals(c)), "the id must not contain any slashes")
  require(!name.exists(_.isWhitespace), "the id must not contain any whitespace characters")

  def addItem(item: MenuItem): Unit = {
    children = children :+ item
  }

}

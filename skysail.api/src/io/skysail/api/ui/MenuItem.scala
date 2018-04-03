package io.skysail.api.ui

case class MenuItem(segmentId: String, url: String, items: List[MenuItem] = List()) {

  require(segmentId != null, "the id of a MenuItem must not null")
  require(!segmentId.exists(c => '/'.equals(c)), "the id must not contain any slashes")
  require(!segmentId.exists(c => '\\'.equals(c)), "the id must not contain any slashes")
  require(!segmentId.exists(_.isWhitespace), "the id must not contain any whitespace characters")

  def addItem(item: MenuItem): MenuItem = {
    copy(items = items :+ item)
  }

  def update(changedItem: MenuItem): MenuItem = {
    copy(items = items.map(i => if (i.segmentId == changedItem.segmentId) changedItem else i))
  }


}

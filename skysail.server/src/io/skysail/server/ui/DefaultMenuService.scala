package io.skysail.server.ui

import io.skysail.api.ui.{MenuItem, MenuService}

class DefaultMenuService extends MenuService {

  override var root = MenuItem("", "")

  override def register(itemPath: String, subItem: MenuItem): MenuItem = {
    val item = find(itemPath)
      .getOrElse(createWithParents(itemPath))
    val newItem = item.addItem(subItem)
    updateParents(itemPath, newItem)
    newItem
  }

  private def updateParents(itemPath: String, changedItem: MenuItem): Unit = {
    if (itemPath == "") {
      root = changedItem
    } else {
      val segments = itemPath.split("/").toList
      val parentPath = segments.reverse.tail.reverse.mkString("/")
      val p = find(parentPath)
        .getOrElse(throw new IllegalArgumentException(s"could not find menu item with path '$itemPath'"))
      updateParents(parentPath, p.update(changedItem))
    }
  }

  override def find(path: String): Option[MenuItem] = {
    lookup(root, path)
  }

  private def lookup(m: MenuItem, path: String): Option[MenuItem] = {
    if ("".equals(path)) {
      Some(m)
    } else {
      val segments = path.split("/").toList
      m.items
        .find(_.segmentId == segments.head)
        .flatMap(c => lookup(c, segments.tail.mkString("/")))
        .orElse(None)

    }
  }

  private def createWithParents(path: String): MenuItem = {
    val segments = path.split("/").toList.reverse
    val t = segments.tail.reverse.mkString("/")
    val newItem = MenuItem(segments.head,null)
    register(t, newItem)
    newItem
  }


}

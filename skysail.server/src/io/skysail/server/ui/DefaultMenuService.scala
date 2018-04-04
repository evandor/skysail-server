package io.skysail.server.ui

import java.util.concurrent.atomic.AtomicInteger

import io.skysail.api.ui.{MenuItem, MenuService}

class DefaultMenuService extends MenuService {

  private val identifier = new AtomicInteger(1)

  override var root = MenuItem("", "")

  override def register(parentItemPath: String, newSubItem: MenuItem): MenuItem = {
    newSubItem.id = identifier.getAndIncrement()
    val parentItem = find(parentItemPath)
      .getOrElse(createWithParents(parentItemPath))
    val parentId = parentItem.id
    val newParentItem = parentItem.addItem(newSubItem)
    newParentItem.id = parentId
    updateParents(parentItemPath, newParentItem)
    newParentItem
  }

  override def find(path: String): Option[MenuItem] = {
    lookup(root, path)
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

  private def lookup(m: MenuItem, path: String): Option[MenuItem] = {
    if ("".equals(path)) {
      Some(m)
    } else {
      val segments = path.split("/").toList
      m.children
        .find(_.name == segments.head)
        .flatMap(c => lookup(c, segments.tail.mkString("/")))
        .orElse(None)

    }
  }

  private def createWithParents(path: String): MenuItem = {
    val segments = path.split("/").toList.reverse
    val t = segments.tail.reverse.mkString("/")
    val newItem = MenuItem(segments.head,null)
    newItem.id = identifier.getAndIncrement()
    register(t, newItem)
    newItem
  }


}

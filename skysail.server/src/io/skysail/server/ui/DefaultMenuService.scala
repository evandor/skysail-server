package io.skysail.server.ui

import java.util.concurrent.atomic.AtomicInteger

import io.skysail.api.ui.{MenuItem, MenuService}

class DefaultMenuService extends MenuService {

  private val identifier = new AtomicInteger(2)

  override var root = new MenuItem("", "")

  override def register(parentItemPath: String, newSubItem: MenuItem): MenuItem = {
    newSubItem.id = identifier.getAndIncrement()
    val parentItem = find(parentItemPath)
      .getOrElse(createWithParents(parentItemPath))
    val parentId = parentItem.id
    val newParentItem = parentItem.addItem(newSubItem)
    parentItem
  }

  override def find(path: String): Option[MenuItem] = {
    lookup(root, path)
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
    val newItem = new MenuItem(segments.head,null)
    newItem.id = identifier.getAndIncrement()
    register(t, newItem)
    newItem
  }


}

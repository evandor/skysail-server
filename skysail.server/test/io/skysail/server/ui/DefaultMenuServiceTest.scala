package io.skysail.server.ui

import io.skysail.api.ui.{MenuItem, MenuService}
import org.scalatest.{BeforeAndAfter, FlatSpec}

class DefaultMenuServiceTest extends FlatSpec with BeforeAndAfter {

  var menuService: MenuService = _

  before {
    menuService = new DefaultMenuService()
  }

  "An initial DefaultMenuService" should "provide the empty menu root at ''" in {
    assert(menuService.root.items.isEmpty)
    assert(menuService.root.segmentId == "")
    assert(menuService.root.url == "")
  }

  "A DefaultMenuService" should "accept a menuItem registration" in {
    menuService.register("", MenuItem("sub", "sublink"))
    assert(menuService.root.items.size == 1)
  }

  "A registered menuItem" should "be retrievable using its concatenated path" in {
    val subMenu = MenuItem("sub", "sub")
    menuService.register("", subMenu)
    val res:Option[MenuItem] = menuService.find("sub")
    assert(res.isDefined)
    assert(res.get.segmentId == "sub")
  }

  "A registered sub menuItem" should "be retrievable using its concatenated path2" in {
    menuService.register("", MenuItem("a", "/A"))
    menuService.register("a", MenuItem("b", "/A/B"))
    menuService.register("a/b", MenuItem("c", "A/B/C"))
  }

  "Registering menuItems" should "leave the parent chain intact" in {
    menuService.register("", MenuItem("sub", "sub"))
    menuService.register("sub", MenuItem("subsub", "subsub"))

    assert(menuService.find("").get.segmentId == "")
    assert(menuService.find("").get.items.size == 1)

    assert(menuService.find("sub").get.segmentId == "sub")
    assert(menuService.find("sub").get.items.size == 1)

    assert(menuService.find("sub/subsub").get.segmentId == "subsub")
    assert(menuService.find("sub/subsub").get.items.isEmpty)
  }

  "Registering a menuItem at an unknown simple path" should "create the parent" in {
    menuService.register("sub", MenuItem("subsub", "subsub"))

    assert(menuService.find("").get.segmentId == "")
    assert(menuService.find("").get.items.size == 1)

    assert(menuService.find("sub").get.segmentId == "sub")
    assert(menuService.find("sub").get.items.size == 1)

    assert(menuService.find("sub/subsub").get.segmentId == "subsub")
    assert(menuService.find("sub/subsub").get.items.isEmpty)
  }

  "Registering a menuItem at an unknown concatenated path" should "create the parent chain" in {
    menuService.register("sub/sub2", MenuItem("sub3", "link"))

    assert(menuService.find("").get.segmentId == "")
    assert(menuService.find("").get.items.size == 1)

    assert(menuService.find("sub").get.segmentId == "sub")
    assert(menuService.find("sub").get.items.size == 1)

    assert(menuService.find("sub/sub2").get.segmentId == "sub2")
    assert(menuService.find("sub/sub2").get.items.size == 1)

    assert(menuService.find("sub/sub2/sub3").get.segmentId == "sub3")
    assert(menuService.find("sub/sub2/sub3").get.items.isEmpty)

  }

}
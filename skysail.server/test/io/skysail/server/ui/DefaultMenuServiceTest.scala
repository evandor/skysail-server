package io.skysail.server.ui

import io.skysail.api.ui.{MenuItem, MenuService}
import org.scalatest.{BeforeAndAfter, FlatSpec}

class DefaultMenuServiceTest extends FlatSpec with BeforeAndAfter {

  var menuService: MenuService = _

  before {
    menuService = new DefaultMenuService()
  }

  "An initial DefaultMenuService" should "provide the empty menu root at ''" in {
    assert(menuService.root.nodes.isEmpty)
    assert(menuService.root.text == "")
    assert(menuService.root.url == "")
  }

  "A DefaultMenuService" should "accept a menuItem registration" in {
    menuService.register("", MenuItem("sub", "sublink"))
    assert(menuService.root.nodes.size == 1)
  }

  "A registered menuItem" should "be retrievable using its concatenated path" in {
    val subMenu = MenuItem("sub", "sub")
    menuService.register("", subMenu)
    val res:Option[MenuItem] = menuService.find("sub")
    assert(res.isDefined)
    assert(res.get.text == "sub")
  }

  "A registered sub menuItem" should "be retrievable using its concatenated path2" in {
    menuService.register("", MenuItem("a", "/A"))
    menuService.register("a", MenuItem("b", "/A/B"))
    menuService.register("a/b", MenuItem("c", "A/B/C"))
  }

  "Registering menuItems" should "leave the parent chain intact" in {
    menuService.register("", MenuItem("sub", "sub"))
    menuService.register("sub", MenuItem("subsub", "subsub"))

    assert(menuService.find("").get.text == "")
    assert(menuService.find("").get.nodes.size == 1)

    assert(menuService.find("sub").get.text == "sub")
    assert(menuService.find("sub").get.nodes.size == 1)

    assert(menuService.find("sub/subsub").get.text == "subsub")
    assert(menuService.find("sub/subsub").get.nodes.isEmpty)
  }

  "Registering a menuItem at an unknown simple path" should "create the parent" in {
    menuService.register("sub", MenuItem("subsub", "subsub"))

    assert(menuService.find("").get.text == "")
    assert(menuService.find("").get.nodes.size == 1)

    assert(menuService.find("sub").get.text == "sub")
    assert(menuService.find("sub").get.nodes.size == 1)

    assert(menuService.find("sub/subsub").get.text == "subsub")
    assert(menuService.find("sub/subsub").get.nodes.isEmpty)
  }

  "Registering a menuItem at an unknown concatenated path" should "create the parent chain" in {
    menuService.register("sub/sub2", MenuItem("sub3", "link"))

    assert(menuService.find("").get.text == "")
    assert(menuService.find("").get.nodes.size == 1)

    assert(menuService.find("sub").get.text == "sub")
    assert(menuService.find("sub").get.nodes.size == 1)

    assert(menuService.find("sub/sub2").get.text == "sub2")
    assert(menuService.find("sub/sub2").get.nodes.size == 1)

    assert(menuService.find("sub/sub2/sub3").get.text == "sub3")
    assert(menuService.find("sub/sub2/sub3").get.nodes.isEmpty)

  }

}
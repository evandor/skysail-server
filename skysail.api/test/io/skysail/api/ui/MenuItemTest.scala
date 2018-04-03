package io.skysail.api.ui

import org.scalatest.{BeforeAndAfter, FlatSpec}

class MenuItemTest extends FlatSpec with BeforeAndAfter {

  "A simple MenuItem" should "have the expected properties" in {
    val simple = MenuItem("simple", "link1")
    assert(simple.segmentId == "simple")
    assert(simple.items.isEmpty)
    assert(simple.toString == "MenuItem(simple,link1,List())")
  }

  "In a MenuItem chain - created with the constructor - with two elements the parent element" should "have the expected properties" in {
    val child = MenuItem("child", "link2")
    val parent = MenuItem("parent", "link1", List(child))
    assert(parent.segmentId == "parent")
    assert(parent.items.size == 1)
    assert(parent.toString == "MenuItem(parent,link1,List(MenuItem(child,link2,List())))")
  }

  "In a MenuItem chain - created with the add method - with two elements the parent element" should "have the expected properties" in {
    val child = MenuItem("child", "link2")
    val parent = MenuItem("parent", "link1").addItem(child)
    assert(parent.segmentId == "parent")
    assert(parent.items.size == 1)
    assert(parent.toString == "MenuItem(parent,link1,List(MenuItem(child,link2,List())))")
  }

}

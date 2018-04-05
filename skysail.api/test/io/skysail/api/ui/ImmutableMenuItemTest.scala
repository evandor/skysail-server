package io.skysail.api.ui

import org.scalatest.{BeforeAndAfter, FlatSpec}

class ImmutableMenuItemTest extends FlatSpec with BeforeAndAfter {

  "A simple MenuItem" should "have the expected properties" in {
    val simple = ImmutableMenuItem("simple", "link1")
    assert(simple.name == "simple")
    assert(simple.children.isEmpty)
    assert(simple.toString == "MenuItem(simple,link1,List())")
  }

  "In a MenuItem chain - created with the constructor - with two elements the parent element" should "have the expected properties" in {
    val child = ImmutableMenuItem("child", "link2")
    val parent = ImmutableMenuItem("parent", "link1", List(child))
    assert(parent.name == "parent")
    assert(parent.children.size == 1)
    assert(parent.toString == "MenuItem(parent,link1,List(MenuItem(child,link2,List())))")
  }

  "In a MenuItem chain - created with the add method - with two elements the parent element" should "have the expected properties" in {
    val child = ImmutableMenuItem("child", "link2")
    val parent = ImmutableMenuItem("parent", "link1").addItem(child)
    assert(parent.name == "parent")
    assert(parent.children.size == 1)
    assert(parent.toString == "MenuItem(parent,link1,List(MenuItem(child,link2,List())))")
  }

}

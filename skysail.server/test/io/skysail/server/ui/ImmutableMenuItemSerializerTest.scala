package io.skysail.server.ui

import io.skysail.api.ui.ImmutableMenuItem
import org.json4s.native.Serialization.write
import org.scalatest.FlatSpec

class ImmutableMenuItemSerializerTest extends FlatSpec {

  "a" should "b" in {
    implicit val fmt = org.json4s.DefaultFormats + new MenuItemSerializer()
    val i = ImmutableMenuItem("name", "url", List(ImmutableMenuItem("a", "/a"), ImmutableMenuItem("b", "/b", List(ImmutableMenuItem("c", "/b/c")))))
    val r = write(i)
    println(r)
  }
}

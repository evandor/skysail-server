package io.skysail.server.ui

import io.skysail.api.ui.MenuItem
import org.json4s.native.Serialization.write
import org.scalatest.FlatSpec

class MenuItemSerializerTest extends FlatSpec {

  "a" should "b" in {
    implicit val fmt = org.json4s.DefaultFormats + new MenuItemSerializer()
    val i = MenuItem("name", "url")
    val r = write(i)
    println(r)
  }
}

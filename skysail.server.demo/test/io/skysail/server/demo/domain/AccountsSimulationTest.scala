package io.skysail.server.demo.domain

import org.junit.Test
import org.json4s._
import org.json4s.jackson.JsonMethods._

class AccountsSimulationTest {

  @Test
  def test() {
//    var input = """
//    {
//      "id":"532bcabc-bd01-4f8f-b3f6-90a1dcdfb6b2",
//      "out_from":[{"out":"#96:6","in":{"id":"0081b4ff-231b-4c52-9061-6412f4852de6","title":"ing-diba","initial":1000,
//      "in_from":["#98:1"]}}],"out_to":[{"out":"#96:6","in":{"id":"f41b8ca5-5378-45d5-9998-1a2511a63b08",
//      "title":"another test",
//      "initial":0,
//      "in_to":["#99:0"]}}],
//      "amount":1000}
//    """
    var input = """

    {
      "id":"1148f9fa-d677-4d69-9138-805a945a5752",
      "out_from":[
        {
          "out":"#81:1",
          "in":{
            "id":"aacf38d3-55b7-4a3a-9630-67f5d88274ff",
            "title":"test",
            "initial":10,
            "in_from":["#83:1"]
          }
        }
      ],
      "amount":99
    }

    """

    val ast = parse(input)
    implicit val formats = DefaultFormats

    val r = ast.extract[OrientPattern2]
    println(r)
  }

  @Test
  def test2() {
    var input = """
    {
      "id":"532bcabc-bd01-4f8f-b3f6-90a1dcdfb6b2",
      "out_from":[
        {
          "out":"#96:6",
          "in":{
            "id":"0081b4ff-231b-4c52-9061-6412f4852de6",
            "title":"ing-diba",
            "initial":1000,
            "in_from":["#98:1"]
          }
        }
      ],
      "title":"another test",
      "initial":0,
      "in_to":[
        "#99:0"
      ]}}],
      "amount":1000
    }
    """
    //       "out_to":[{"out":"#96:6","in":{"id":"f41b8ca5-5378-45d5-9998-1a2511a63b08",

    val ast = parse(input)
    implicit val formats = DefaultFormats

    val r = ast.extract[OrientPattern2]
    println(r)
  }

  @Test
  def test3() {
    implicit val serialization = jackson.Serialization
    implicit val formats = DefaultFormats

    val a = OrientAccount("id", "title", 10)
    val nodes = List(OrientNode("#96:6", a))
    val p = OrientPattern2("pid", nodes, 100)
    val e: JValue = Extraction.decompose(p)
    println(e)
    println(compact(render(e)))
  }

  @Test
  def test4(): Unit = {
    var input =
      """
        {
        "id":"pid",
        "out_from":[
          {
            "out":"#96:6",
            "in":{
              "id":"id",
              "title":"title",
              "initial":10
              }
          }
        ],
        "amount":100}
      """
    val ast = parse(input)
    implicit val formats = DefaultFormats

    val r = ast.extract[OrientPattern2]
    println(r)
  }
}
package io.skysail.server.demo.domain

import org.junit.Test
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.junit.Ignore

class AccountsSimulationTest {

  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

  @Test
  @Ignore
  def create_orientdb_json_and_parse_back_to_entity() {

    val from = Account(Some("from_account"), "from_a", 10)
    val to = Account(Some("to_account"), "to_a", 100)
    val p = Pattern(Some("pid"), from, to, 100)
    val e: JValue = Extraction.decompose(p)
    println(e)
    println(compact(render(e)))
  }

  @Test
  @Ignore
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

    val r = ast.extract[OrientPattern]
    println(r)
  }

  @Test
  @Ignore
  def test2() {
    //    var input = """
    //    {
    //      "id":"532bcabc-bd01-4f8f-b3f6-90a1dcdfb6b2",
    //      "out_from":[
    //        {
    //          "out":"#96:6",
    //          "in":{
    //            "id":"0081b4ff-231b-4c52-9061-6412f4852de6",
    //            "title":"ing-diba",
    //            "initial":1000,
    //            "in_from":["#98:1"]
    //          }
    //        }
    //      ],
    //      "title":"another test",
    //      "initial":0,
    //      "in_to":[
    //        "#99:0"
    //      ]}}],
    //      "amount":1000
    //    }
    //    """
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
      "out_to":[
        {
          "out":"#96:6",
          "in":{
            "id":"f41b8ca5-5378-45d5-9998-1a2511a63b08",
            "title":"another test",
            "initial":0,
            "in_to":["#99:0"]
          }
        }
      ],
      "amount":1000
    }

"""

    val ast = parse(input)
    implicit val formats = DefaultFormats

    println(ast)

    val r = ast.extract[OrientPattern]
    println(r)
  }

  @Test
  @Ignore
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

    val r = ast.extract[OrientPattern]
    println(r)
  }

  @Test
  @Ignore
  def test5(): Unit = {
    var input = """
{
  "@rid": "#96:7",
  "@version": 1,
  "id": "6040d495-ddf9-4aa9-a860-23248b2bb948",
  "out_from": [
    {
      "@rid": "#98:2",
      "@version": 1,
      "out": "#96:7",
      "in": {
        "@rid": "#94:4",
        "@version": 3,
        "id": "0081b4ff-231b-4c52-9061-6412f4852de6",
        "title": "ing-diba",
        "initial": 1000,
        "in_from": [
          {
            "@rid": "#98:1",
            "@version": 1,
            "out": {
              "@rid": "#96:6",
              "@version": 2,
              "id": "532bcabc-bd01-4f8f-b3f6-90a1dcdfb6b2",
              "out_from": [
                "#98:1"
              ],
              "out_to": [
                {
                  "@rid": "#99:0",
                  "@version": 1,
                  "out": "#96:6",
                  "in": {
                    "@rid": "#94:6",
                    "@version": 3,
                    "id": "f41b8ca5-5378-45d5-9998-1a2511a63b08",
                    "title": "another test",
                    "initial": 0,
                    "in_to": [
                      "#99:0",
                      {
                        "@rid": "#99:1",
                        "@version": 1,
                        "out": "#96:7",
                        "in": "#94:6"
                      }
                    ]
                  }
                }
              ],
              "amount": 1000
            },
            "in": "#94:4"
          },
          "#98:2"
        ]
      }
    }
  ],
  "out_to": [
    "#99:1"
  ],
  "amount": 999
}

"""

    val ast = parse(input)
    implicit val formats = DefaultFormats

    val r = ast.extract[OrientPattern]
    println(r)

  }

  @Test
  @Ignore
  def test6(): Unit = {
    var input = """
{
  "@rid": "#96:8",
  "@version": 1,
  "id": "9e4ccd49-5d2e-4c0a-b685-1f97f08f5dde",
  "out_from": [
    {
      "@rid": "#98:3",
      "@version": 1,
      "out": "#96:8",
      "in": {
        "@rid": "#94:4",
        "@version": 8,
        "in_from": [
          "#98:3"
        ],
        "id": "0081b4ff-231b-4c52-9061-6412f4852de6",
        "title": "ing-diba",
        "initial": 1000
      }
    }
  ],
  "out_to": [
    {
      "@rid": "#99:2",
      "@version": 1,
      "out": "#96:8",
      "in": {
        "@rid": "#94:3",
        "@version": 6,
        "in_to": [
          "#99:2"
        ],
        "id": "b9fb9581-cbce-4884-be07-cd115e78da62",
        "title": "title",
        "initial": 0
      }
    }
  ],
  "amount": 0
}
"""

    val ast = parse(input)
    implicit val formats = DefaultFormats

    println("AST:" + ast)

    println("out_from: " + ast \\ "out_from")
    println("in:       " + ast \\ "out_from" \ "in")

    println("r1: " + compact(render(ast)))

    val in = (ast \\ "out_from" \ "in").asInstanceOf[JValue]
    val p = ast transformField {
      case JField("out_from", JArray(s)) => ("from", in)
    }

    println("r2: " + compact(render(p)))

    //val s = ast removeField { _ == JField("out_from", JString("Marilyn")) }
    //val s = ast removeField { case JField("out_from", _) => false }
    //val s = ast removeField { case _ => true }

    //println("SSS: " + compact(render(s)))

    println(p.extract[OrientPattern])

    // val r = p.extract[Pattern]
    // println(r)

  }

  @Test
  @Ignore
  def test7(): Unit = {
    var input = """
{
  "id": "9e4ccd49-5d2e-4c0a-b685-1f97f08f5dde",
  "out_from": [
    {
      "out": "#96:8",
      "in": {
        "in_from": [
          "#98:3"
        ],
        "id": "0081b4ff-231b-4c52-9061-6412f4852de6",
        "title": "ing-diba",
        "initial": 1000
      }
    }
  ],
  "out_to": [
    {
      "out": "#96:8",
      "in": {
        "in_to": [
          "#99:2"
        ],
        "id": "b9fb9581-cbce-4884-be07-cd115e78da62",
        "title": "title",
        "initial": 0
      }
    }
  ],
  "amount": 0
}
"""

    val ast = parse(input)
    implicit val formats = DefaultFormats
    val from = (ast \\ "out_from" \ "in")(0)
    val to = (ast \\ "out_to" \ "in")(0)
    val p = ast transformField {
      case JField("out_from", JArray(s)) => ("from", from)
      case JField("out_to", JArray(s)) => ("to", to)
    }
    println(p.extract[Pattern])
  }

    @Test
  def test8(): Unit = {
    var input = """
{
  "id": "9e4ccd49-5d2e-4c0a-b685-1f97f08f5dde",
  "out_from": [
    {
      "out": "#96:8",
      "in": {
        "in_from": [
          "#98:3"
        ],
        "id": "0081b4ff-231b-4c52-9061-6412f4852de6",
        "title": "ing-diba",
        "initial": 1000
      }
    }
  ],
  "out_to": [
    {
      "out": "#96:8",
      "in": {
        "in_to": [
          "#99:2"
        ],
        "id": "b9fb9581-cbce-4884-be07-cd115e78da62",
        "title": "title",
        "initial": 0
      }
    }
  ],
  "amount": 0
}
"""

    val ast = parse(input)
    println("AST: " + ast)
    implicit val formats = DefaultFormats
    val from = (ast \\ "out_from" \ "in")(0)
    val to = (ast \\ "out_to" \ "in")(0)
    val p = ast transformField {
      case JField("out_from", JArray(s)) => ("from", from)
      case JField("out_to", JArray(s)) => ("to", to)
    }
    println(p)
    println(p.extract[Pattern])
  }

}
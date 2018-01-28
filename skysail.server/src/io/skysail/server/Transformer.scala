//package io.skysail.server
//
//import org.json4s.DefaultFormats
//import org.json4s.jackson.JsonMethods.parse
//
//object Transformer {
//
//  def jsonStringToBean[T](jsonStr: String): T = {
//    val ast = parse(jsonStr)
//    println(ast)
//    implicit val formats = DefaultFormats
//    println("AST" + ast)
//    ast.extract[T]
//  }
//
//}

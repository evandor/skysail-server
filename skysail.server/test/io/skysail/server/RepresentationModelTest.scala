//package io.skysail.server
//
//import io.skysail.domain.{ResponseEvent, ResponseEventBase}
//import org.junit.{Before, Test}
//import org.scalatest.FunSuite
//import org.junit.Ignore
//
//class RepresentationModelTest {
//
//  var rep: RepresentationModel = _
//
//  case class TheEntity(id: String)
//
//  @Before
//  def setUp() {
//    val response = ResponseEvent(null, TheEntity("id"))
//    rep = new RepresentationModel(response, null)
//
//  }
//  @Test
//  @Ignore
//  def test() {
//    rep.getString("$.store.book[0].title")
//  }
//}

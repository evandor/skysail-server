package io.skysail.server.demo

import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.FormData
import akka.http.scaladsl.model.StatusCodes._
import io.skysail.server.demo.domain.Bookmark
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.skysail.server.demo.PostBoorkmarkRoutesTest._


object PostBoorkmarkRoutesTest {
  val POST_URL = "/demo/v1/bms/"
}

@RunWith(classOf[JUnitRunner])
class PostBoorkmarkRoutesTest extends DemoApplicationTest {

  "A GET request to the PostBookmarksResource" should {

    "return the html page if no accept header was set" in {
      Get(POST_URL) ~> router ~> check {
        status shouldBe OK
        contentType shouldBe `text/html(UTF-8)`
        responseAs[String] should include("submit")
      }
    }

    "return the json representation if an accept header for application/json is sent" in {
      Get(POST_URL).addHeader(applicationJsonAcceptHeader) ~> router ~> check {
        status shouldBe OK
        //contentType shouldBe `application/json`
        responseAs[String] should include("{\"title\":\"\",\"url\":\"\"}")
      }
    }
  }

  "A POST request to the PostBookmarksResource" should {

    "redirect to the BookmarksResource page if no accept header was set" in {
      Post(POST_URL).withEntity(FormData(Map("title" -> "t", "url" -> "http://url")).toEntity) ~> router ~> check {
        status shouldBe SeeOther
        header("location").toString() shouldBe "Some(Location: /demo/v1/bms)"
        contentType shouldBe `text/html(UTF-8)`
      }
    }

    "create a new bookmark which can be found using a GET request on the BookmarksResource" in {
      create(Bookmark(None, "some title", "someurl"))
      Thread.sleep(10)
      Get("/demo/v1/bms") ~> router ~> check {
        responseAs[String] should include("someurl")
      }
    }

  }

//  "A PUT request to the PostBookmarksResource" should {
//    "return method not allowed status" in {
//      Put(POST_URL) ~> router ~> check {
//        status shouldBe MethodNotAllowed
//      }
//    }
//  }

  "A DELETE request to the PostBookmarksResource" should {
    "return method not allowed status" in {
      Delete(POST_URL) ~> router ~> check {
        status shouldBe MethodNotAllowed
      }
    }
  }

  private def create(bm: Bookmark) = {
    Post(POST_URL).withEntity(FormData(Map("title" -> bm.title, "url" -> bm.url)).toEntity) ~> router
  }


}

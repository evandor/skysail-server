package io.skysail.domain.model

import java.net.URL

import akka.http.scaladsl.model.Uri
import io.skysail.domain.routes.RouteMapping
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FlatSpec}

@RunWith(classOf[JUnitRunner])
class ResourceModelTest extends FlatSpec with BeforeAndAfterEach {

  "A ResourceModel" should "provide its name" in {
    val resModel = ResourceModel(RouteMapping("/str", null, classOf[OrdersResource]))
    assert(resModel.entityClass.toString == "io.skysail.domain.model.Order")
  }

  "A ResourceModel" should "provide its entity class" in {
    val resModel = ResourceModel(RouteMapping("str", null, classOf[OrderResource]))
    assert(resModel.entityClass.toString == "io.skysail.domain.model.Order")
  }

  "A ResourceModel" should "match the uri" in {
    val resModel = ResourceModel(RouteMapping("/some/path", null, classOf[OrderResource]))
    assert(resModel.matchPath(Uri("http://localhost:8080/some/path")))
    assert(!resModel.matchPath(Uri("http://localhost:8080/another/path")))
  }
}
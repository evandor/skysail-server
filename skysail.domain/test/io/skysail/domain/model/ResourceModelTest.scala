package io.skysail.domain.model

import io.skysail.domain.routes.RouteMapping
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FlatSpec}

@RunWith(classOf[JUnitRunner])
class ResourceModelTest extends FlatSpec with BeforeAndAfterEach {

  "An ResourceModel" should "provide its name" in {
    val resModel = ResourceModel(RouteMapping("str", null, classOf[OrdersResource]))
    assert(resModel.entityClass.toString == "io.skysail.domain.model.Order")
  }

  "An ResourceModel" should "provide its entity class" in {
    val resModel = ResourceModel(RouteMapping("str", null, classOf[OrderResource]))
    assert(resModel.entityClass.toString == "io.skysail.domain.model.Order")
  }
}
package io.skysail.domain.model

import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.RouteMapping
import org.scalatest.{BeforeAndAfterEach, FlatSpec, FunSuite}

class ResourceModelTest extends FlatSpec with BeforeAndAfterEach {

  "An ResourceModel" should "provide its name" in {
    val resModel = ResourceModel(RouteMapping("str", null, classOf[OrdersResource]))
    assert(resModel.entityClass.toString == "")
  }
}
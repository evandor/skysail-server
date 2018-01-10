package io.skysail.domain.model

import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.RouteMapping
import org.scalatest.{BeforeAndAfterEach, FlatSpec}

class ApplicationModelTest extends FlatSpec with BeforeAndAfterEach {



  override def beforeEach() {

  }

  "An ApplicationModel" should "provide its name" in  {
    val appModel = ApplicationModel("name", ApiVersion(1), "desc")
    assert(appModel.name == "name")
  }

  "An ApplicationModel" should "provide its version" in  {
    val appModel = ApplicationModel("name", ApiVersion(1), "desc")
    assert(appModel.apiVersion.toString() == "v1")
  }

  "An ApplicationModel" should "provide its description" in  {
    val appModel = ApplicationModel("name", ApiVersion(1), "desc")
    assert(appModel.description == "desc")
  }

  "An ApplicationModel" should "accept a valid route mapping" in  {
    val appModel = ApplicationModel("name", ApiVersion(1), "desc")
    val t = appModel.addResourceModel(RouteMapping("str", null, classOf[OrdersResource]))
    assert(t.isDefined)
    assert(t.get.toString == "io.skysail.domain.model.OrdersResource")
  }

}

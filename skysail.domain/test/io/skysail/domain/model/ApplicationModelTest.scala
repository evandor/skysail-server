package io.skysail.domain.model

import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.ListRouteMapping
import org.scalatest.{BeforeAndAfterEach, FlatSpec}

class ApplicationModelTest extends FlatSpec with BeforeAndAfterEach {

  class TestApp {}
  class TestEntity {}

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


  "An ApplicationModel" should "provide its version" in  {
    var routeMapping = ListRouteMapping[TestApp, TestEntity]("str", null, null)
    val appModel = ApplicationModel("name", ApiVersion(1), "desc")
    appModel.addResourceModel(routeMapping)
  }

}

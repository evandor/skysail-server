package io.skysail.domain.model

import io.skysail.domain.app.ApiVersion
import io.skysail.domain.routes.RouteMapping
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FlatSpec}

@RunWith(classOf[JUnitRunner])
class ApplicationModelTest extends FlatSpec with BeforeAndAfterEach {

  var appModel: ApplicationModel = _

  override def beforeEach() {
    appModel = ApplicationModel("name", ApiVersion(1), "desc")
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
    val t = appModel.addResourceModel(RouteMapping("str", null, classOf[OrdersResource]))
    assert(t.isDefined)
    assert(t.get.toString == "io.skysail.domain.model.Order")
  }

  "An ApplicationModel" should "return the entity for a given url" in {
    appModel.addResourceModel(RouteMapping("str", null, classOf[OrdersResource]))
    //appModel.puml.entityModelFor(new URL("test"))
  }

  "An ApplicationModel" should "do" in {
    appModel.addResourceModel(RouteMapping("str", null, classOf[OrdersResource]))
    assert(!appModel.entityRelationExists(classOf[String], "item"))
    assert(!appModel.entityRelationExists(classOf[String], "price"))
  }

}

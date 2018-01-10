package io.skysail.domain.model

import io.skysail.domain.{RequestEvent, SkysailResource}
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.resources.ListResource

class TestApp extends ApplicationApi {}

case class Order(id: Option[String], item: String, price: Double)

class OrdersResource extends ListResource[TestApp, Order] {
  override def getList(re: RequestEvent): List[Order] = List(Order(Some("123"), "a book", 12.0))
}
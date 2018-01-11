package io.skysail.domain.model

import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.resources.{EntityResource, ListResource}
import io.skysail.domain.{RequestEvent, ResponseEventBase}

class TestApp extends ApplicationApi {}

case class Order(id: Option[String], item: String, price: Double)

class OrdersResource extends ListResource[TestApp, Order] {
  override def getList(re: RequestEvent): List[Order] = List(Order(Some("123"), "a book", 12.0))
}

class OrderResource extends EntityResource[TestApp, Order] {

  override def getEntity(re: RequestEvent): Option[Order] = {
    Some(Order(None,"another book", 24))
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
}
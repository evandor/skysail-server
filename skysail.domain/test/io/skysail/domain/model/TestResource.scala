package io.skysail.domain.model

import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}

class TestApp extends ApplicationApi {}

case class Item(name: String, price: Double)
case class Address(adr: String)
case class Order(id: Option[String], name: String, price: Double, items:List[Item], address: Address)

class OrdersResource extends EntityResource[TestApp, Order] {
  //override def getList(re: RequestEvent): List[Order] = List(Order(Some("123"), "a book", 12.0))
  override def getEntity(re: RequestEvent): Option[Order] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

}

class OrderResource extends EntityResource[TestApp, Order] {

  override def getEntity(re: RequestEvent): Option[Order] = {
    Some(Order(None,"another book", 24, List(), null))
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???


}
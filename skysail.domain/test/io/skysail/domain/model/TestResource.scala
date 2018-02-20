package io.skysail.domain.model

import akka.actor.ActorSystem
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}

class TestApp extends ApplicationApi {}

case class Order(id: Option[String], item: String, price: Double)

class OrdersResource extends EntityResource[TestApp, Order] {
  //override def getList(re: RequestEvent): List[Order] = List(Order(Some("123"), "a book", 12.0))
  override def getEntity(re: RequestEvent): Option[Order] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???

}

class OrderResource extends EntityResource[TestApp, Order] {

  override def getEntity(re: RequestEvent): Option[Order] = {
    Some(Order(None,"another book", 24))
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???

}
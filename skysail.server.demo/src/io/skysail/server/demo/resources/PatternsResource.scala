package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{ListResource, PostResource}
import io.skysail.domain.{RedirectResponseEvent, RequestEvent, ResponseEvent}
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Account, Pattern}
import spray.json.{DefaultJsonProtocol, _}

trait JsonSupport7 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val AccountFormat: RootJsonFormat[Account] = jsonFormat3(Account)
  implicit val PatternFormat: RootJsonFormat[Pattern] = jsonFormat5(Pattern)
}

class PatternsResource extends ListResource[DemoApplication, Pattern] {
  override def getList(re: RequestEvent): List[Pattern] = getApplication().patternRepo.find()
}

class PostPatternResource extends PostResource[DemoApplication, Pattern] with JsonSupport7 {


  def get(requestEvent: RequestEvent): ResponseEvent[Pattern] = {
    //      requestEvent.controllerActor ! applicationModel.entityModelFor(classOf[Pattern]).get.description()
    val f: Int => Boolean = {case _: Int => true}
    val accounts = getApplication().accountsRepo.find()
    ResponseEvent(requestEvent, Pattern(None, null, null, 0, accounts))
  }

  def post(requestEvent: RequestEvent) {
    val b = getApplication().repo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("Pattern created")
    val redirectTo = Some("/demo/v1/bms")
    //requestEvent.controllerActor ! Pattern(Some(b), "a@b.com", "Mira")
    val newRequest = requestEvent.cmd.ctx.request.copy(method = HttpMethods.GET)
    //requestEvent.cmd.ctx.copy(request = newRequest)
    //requestEvent.copy(cmd = requestEvent.cmd.copy(ctx = requestEvent.cmd.ctx.copy)
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = null//Pattern(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}

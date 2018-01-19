package io.skysail.server.demo.resources

import akka.actor.{ ActorSelection, ActorSystem }
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{ ListResource, PostResource }
import io.skysail.domain.{ RedirectResponseEvent, RequestEvent, ResponseEvent }
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{ Account, Pattern }
import spray.json.{ DefaultJsonProtocol, _ }
import java.util.UUID
import java.util.UUID

trait JsonSupport7 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val AccountFormat: RootJsonFormat[Account] = jsonFormat3(Account)
  implicit val PatternFormat: RootJsonFormat[Pattern] = jsonFormat5(Pattern)
}

class PatternsResource extends ListResource[DemoApplication, Pattern] {
  override def getList(re: RequestEvent): List[Pattern] = getApplication().patternRepo.find()
}

class PostPatternResource extends PostResource[DemoApplication, Pattern] with JsonSupport7 {

  def get(requestEvent: RequestEvent): ResponseEvent[Pattern] = {
    val f: Int => Boolean = { case _: Int => true }
    val accounts = getApplication().accountsRepo.find()
    ResponseEvent(requestEvent, Pattern(None, null, null, 0, accounts))
  }

  def post(requestEvent: RequestEvent) {
    val map = requestEvent.cmd.formFieldMap
    
    val entity = Pattern(
          Some(UUID.randomUUID().toString),
          getApplication().accountsRepo.find(map.getOrElse("from", "0")).get,
          getApplication().accountsRepo.find(map.getOrElse("to", "0")).get,
          Integer.parseInt(map.getOrElse("amount", "0")))

    val b = getApplication().patternRepo.save(entity)
    getApplication().eventService.send("Pattern created")
    val redirectTo = Some("/demo/v1/patterns")
    val newRequest = requestEvent.cmd.ctx.request.copy(method = HttpMethods.GET)
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
     // if (getApplication() != null && getApplication().accountsRepo != null) {
//        val entity = Pattern(
//          Some(UUID.randomUUID().toString),
//          null,//getApplication().accountsRepo.find(map.getOrElse("from", "0")).get,
//          null,//getApplication().accountsRepo.find(map.getOrElse("to", "0")).get,
//          Integer.parseInt(map.getOrElse("amount", "0")))
        super.createRoute(applicationActor, processCommand.copy(formFieldMap = map))
     
    }
  }
}

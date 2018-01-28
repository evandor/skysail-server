package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{EntityResource, ListResource, PostResource, PutResource}
import io.skysail.domain.{RedirectResponseEvent, RequestEvent, ResponseEvent, ResponseEventBase}
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Bookmark, Pattern}

//trait JsonSupport7 extends SprayJsonSupport with DefaultJsonProtocol {
//  implicit val AccountFormat: RootJsonFormat[Account] = jsonFormat3(Account)
//  implicit val PatternFormat: RootJsonFormat[Pattern] = jsonFormat5(Pattern)
//}

class PatternsResource extends ListResource[DemoApplication, Pattern] {
  override def getList(re: RequestEvent): List[Pattern] = getApplication().patternRepo.find()
}

class PostPatternResource extends PostResource[DemoApplication, Pattern] {

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
        super.createRoute(applicationActor, processCommand.copy(formFieldMap = map))
    }
  }
}

class PatternResource extends EntityResource[DemoApplication, Pattern] {
  
  //override def get(requestEvent: RequestEvent) = {
  override def getEntity(re: RequestEvent): Option[Pattern] = {
    val app: DemoApplication = getApplication()
    val optionalPattern = app.patternRepo.find(re.cmd.urlParameter.head)
    optionalPattern
  }

  def get(requestEvent: RequestEvent): ResponseEventBase = {
    ???
  }

  
}

class PutPatternResource extends PutResource[DemoApplication, Pattern]  {

  override def get(requestEvent: RequestEvent): ResponseEvent[Pattern] = {
    val e = getApplication().patternRepo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, e.get)
  }

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalBookmark = getApplication().patternRepo.find(requestEvent.cmd.urlParameter.head)
    val e = requestEvent.cmd.entity.asInstanceOf[Pattern]
    val f = e.copy(id = optionalBookmark.get.id)
    getApplication().patternRepo.save(f)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}


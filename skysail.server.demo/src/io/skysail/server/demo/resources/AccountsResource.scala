package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Account, Note}
import spray.json.{DefaultJsonProtocol, _}

trait JsonSupport4 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val NoteFormat: RootJsonFormat[Account] = jsonFormat3(Account)
}

class AccountsResource extends DefaultResource[DemoApplication, Account] {

  override def getList(re: RequestEvent) = getApplication().accountsRepo.find()

  override def getEntity(re: RequestEvent) = getApplication().accountsRepo.find(re.cmd.urlParameter.head)

  override def getTemplate(re: RequestEvent) = Account(None, "", 0)

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/accounts")

  override def getRedirectAfterPut(re: RequestEvent): Option[String] = Some("/demo/v1/accounts")

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem):String = {
    val b = getApplication().accountsRepo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("Account created")
    b
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalNote = getApplication().accountsRepo.find(requestEvent.cmd.urlParameter.head)
    val updatedNote = requestEvent.cmd.entity.asInstanceOf[Note]
    val noteToSave = updatedNote.copy(id = optionalNote.get.id)
    getApplication().accountsRepo.save(noteToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Note(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("content", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

}


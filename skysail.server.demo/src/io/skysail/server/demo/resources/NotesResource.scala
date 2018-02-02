package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Note
import spray.json.{DefaultJsonProtocol, _}

trait JsonSupport3 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val NoteFormat: RootJsonFormat[Note] = jsonFormat3(Note)
}

class NotesResource extends DefaultResource[DemoApplication, Note] {

  override def getList(re: RequestEvent) = getApplication().notesRepo.find()

  override def getEntity(re: RequestEvent) = getApplication().notesRepo.find(re.cmd.urlParameter.head)

  override def getTemplate(re: RequestEvent) = Note(None, "", "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/notes")

  override def getRedirectAfterPut(re: RequestEvent): Option[String] = Some("/demo/v1/notes")

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem):String = {
    val b = getApplication().notesRepo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("Note created")
    b
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalNote = getApplication().notesRepo.find(requestEvent.cmd.urlParameter.head)
    val updatedNote = requestEvent.cmd.entity.asInstanceOf[Note]
    val noteToSave = updatedNote.copy(id = optionalNote.get.id)
    getApplication().notesRepo.save(noteToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map: Map[String, String] =>
      val entity = Note(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("content", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
}


package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ ActorSelection, ActorSystem }
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources._
import io.skysail.domain.{ RedirectResponseEvent, RequestEvent, ResponseEvent }
import io.skysail.server.demo.DemoApplication
import spray.json.{ DefaultJsonProtocol, _ }
import akka.http.scaladsl.model.HttpMethods
import io.skysail.server.demo.domain.Note
import io.skysail.domain.ListResponseEvent

trait JsonSupport3 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val NoteFormat: RootJsonFormat[Note] = jsonFormat3(Note)
}

class NotesResource extends DefaultResource[DemoApplication, Note] {

  override def get(requestEvent: RequestEvent) = {
    val optionalNote = getApplication().notesRepo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalNote.get)
  }

  override def doGetForPostUrl(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! ResponseEvent(requestEvent, Note(None, "", ""))
  }

  override def getList(re: RequestEvent) = ListResponseEvent(re, getApplication().notesRepo.find())

  override def post(requestEvent: RequestEvent) {
    val b = getApplication().notesRepo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("Note created")
    val redirectTo = Some("/demo/v1/notes")
    val newRequest = requestEvent.cmd.ctx.request.copy(method = HttpMethods.GET)
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalNote = getApplication().notesRepo.find(requestEvent.cmd.urlParameter.head)
    val updatedNote = requestEvent.cmd.entity.asInstanceOf[Note]
    val noteToSave = updatedNote.copy(id = optionalNote.get.id)
    getApplication().notesRepo.save(noteToSave)
    val redirectTo = Some("/demo/v1/notes")
    val newRequest = requestEvent.cmd.ctx.request.copy(method = HttpMethods.GET)
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Note(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("content", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  def getEntity(re: RequestEvent): Option[Note] = {
    getApplication().notesRepo.find(re.cmd.urlParameter.head)
  }

}


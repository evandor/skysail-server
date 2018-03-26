package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.domain.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Todo, TodoList}
import spray.json.{DefaultJsonProtocol, _}

trait JsonSupport5 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val TodoFormat: RootJsonFormat[Todo] = jsonFormat3(Todo)
}

class TodosResource extends DefaultResource[DemoApplication, Todo, TodoList] {

  override def repo: RepositoryApi[Todo] = getApplication().todosRepo

  override def getList(re: RequestEvent) = TodoList(getApplication().todosRepo.find())

  override def getEntity(re: RequestEvent) = getApplication().todosRepo.find(re.cmd.urlParameter.head)

  override def getTemplate(re: RequestEvent) = Todo(None, "", "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/todos")

  override def getRedirectAfterPut(re: RequestEvent): Option[String] = Some("/demo/v1/todos")

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): String = {
    val b = getApplication().todosRepo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("Todo created")
    b
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalTodo = getApplication().todosRepo.find(requestEvent.cmd.urlParameter.head)
    val updatedTodo = requestEvent.cmd.entity.asInstanceOf[Todo]
    val entityToSave = updatedTodo.copy(id = optionalTodo.get.id)
    getApplication().todosRepo.save(entityToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Todo(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("content", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

}


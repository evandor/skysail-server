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
import io.skysail.server.demo.domain.DbConfig
import io.skysail.domain.ListResponseEvent

trait JsonSupport2 extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val DbConfigFormat: RootJsonFormat[DbConfig] = jsonFormat3(DbConfig)
}

class DbConfigsResource extends DefaultResource[DemoApplication, DbConfig] {

  override def getList(re: RequestEvent) = getApplication().dbConfigRepo.find()

  override def getEntity(re: RequestEvent) = getApplication().dbConfigRepo.find(re.cmd.urlParameter.head)

  override def getTemplate(re: RequestEvent) = DbConfig(None, "", "")

  def getRedirectAfterPost(re: RequestEvent) = Some("/demo/v1/dbconfigs")

  def getRedirectAfterPut(re: RequestEvent) = Some("/demo/v1/dbconfigs")

  override def get(requestEvent: RequestEvent) = {
    val optionalDbConfig = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalDbConfig.get)
  }

  //  override def handleCreationMappingGet(requestEvent: RequestEvent): Unit = {
  //    requestEvent.controllerActor ! ResponseEvent(requestEvent, DbConfig(None, "", ""))
  //  }

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem) = {
    val b = getApplication().repo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("DbConfig created")
    b
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalDbConfig = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    val updatedDbConfig = requestEvent.cmd.entity.asInstanceOf[DbConfig]
    val DbConfigToSave = updatedDbConfig.copy(id = optionalDbConfig.get.id)
    getApplication().repo.save(DbConfigToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = DbConfig(Some(UUID.randomUUID().toString), map.getOrElse("key", "Unknown"), map.getOrElse("values", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

}


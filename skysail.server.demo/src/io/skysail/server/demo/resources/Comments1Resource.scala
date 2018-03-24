package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Comment1, Comment1List}

class Comments1Resource extends DefaultResource[DemoApplication, Comment1, Comment1List] {

  private val r = getApplication().comments1Repo
  
  override def getList(re: RequestEvent) = Comment1List(r.find())

  override def getEntity(re: RequestEvent): Option[Comment1] = r.find(re.firstParam())

  override def getTemplate(re: RequestEvent) = Comment1(None, "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/comment1s")

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem):String = {
    r.save(requestEvent.cmd.entity)
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalEntity = r.find(requestEvent.firstParam())
    val updatedEntity = requestEvent.cmd.entity.asInstanceOf[Comment1]
    val entityToSave = updatedEntity.copy(id = optionalEntity.get.id)
    r.save(entityToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Comment1(Some(UUID.randomUUID().toString), map.getOrElse("comment", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  override def deleteEntity(re: RequestEvent)(implicit system: ActorSystem): Unit = {
    r.delete(re.cmd.urlParameter.head)
  }

}


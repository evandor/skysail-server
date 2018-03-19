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
  
  override def getList(re: RequestEvent) = Comment1List(getApplication().comments1Repo.find())

  override def getEntity(re: RequestEvent) = getApplication().comments1Repo.find(re.cmd.urlParameter.head)

  override def getTemplate(re: RequestEvent) = Comment1(None, "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/comment1s")

  override def getRedirectAfterPut(re: RequestEvent): Option[String] = Some("/demo/v1/comment1s")

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem):String = {
    getApplication().accountsRepo.save(requestEvent.cmd.entity)
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalAccount = getApplication().accountsRepo.find(requestEvent.cmd.urlParameter.head)
    val updatedAccount = requestEvent.cmd.entity.asInstanceOf[Comment1]
    val AccountToSave = updatedAccount.copy(id = optionalAccount.get.id)
    getApplication().accountsRepo.save(AccountToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Comment1(Some(UUID.randomUUID().toString), map.getOrElse("comment", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

}


package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.domain.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Comment2, Comment2List}

class Comment2Resource extends DefaultResource[DemoApplication, Comment2, Comment2List] {

  override def repo: RepositoryApi[Comment2] = getApplication().comments2Repo

  override def getList(re: RequestEvent) = Comment2List(repo.find())

  override def getTemplate(re: RequestEvent) = Comment2(None, "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/comment1s")

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalAccount = repo.find(requestEvent.cmd.urlParameter.head)
    val updatedAccount = requestEvent.cmd.entity.asInstanceOf[Comment2]
    val AccountToSave = updatedAccount.copy(id = optionalAccount.get.id)
    repo.save(AccountToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Comment2(Some(UUID.randomUUID().toString), map.getOrElse("comment", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

}


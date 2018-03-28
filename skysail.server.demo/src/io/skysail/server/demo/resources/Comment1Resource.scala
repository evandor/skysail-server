package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.server.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Comment1, Comment1List}

class Comment1Resource extends DefaultResource[DemoApplication, Comment1, Comment1List] {
  
  override def getList(re: RequestEvent) = Comment1List(repo.find())

  override def repo: RepositoryApi[Comment1] = getApplication().comments1Repo

  override def getTemplate(re: RequestEvent) = Comment1(None, "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/comment1s")

  override def updateEntity(re: RequestEvent)(implicit system: ActorSystem): Unit = {
    val updatedEntity: Comment1 = re.cmd.entity.asInstanceOf[Comment1]
    val entityToSave: Comment1 = updatedEntity.copy(id = Some(re.firstParam()))
    repo.save(entityToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Comment1(Some(UUID.randomUUID().toString), map.getOrElse("comment", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}


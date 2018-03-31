package io.skysail.server.demo.resources

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Monitor2, Monitor2List}
import io.skysail.server.resources._

class Monitor2Resource extends DefaultResource[DemoApplication, Monitor2, Monitor2List] {
  
  override def getList(re: RequestEvent) = Monitor2List(repo.find())

  override def repo: RepositoryApi[Monitor2] = getApplication().monitor2Repo

  override def getTemplate(re: RequestEvent) = Monitor2(None, "test", "")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/monitor2s")

  override def updateEntity(re: RequestEvent)(implicit system: ActorSystem): Unit = {
    val updatedEntity = re.cmd.entity.asInstanceOf[Monitor2]
    val entityToSave = updatedEntity.copy(id = Some(re.firstParam()))
    repo.save(entityToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Monitor2(None,
        map.getOrElse("name", "Unknown"),
        map.getOrElse("url", "")
      )
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}


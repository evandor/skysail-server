package io.skysail.server.demo.resources

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives.formFieldMap
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.repositories.RepositoryApi
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{DockerContainer, DockerContainerList}
import io.skysail.server.resources.DefaultResource

class ContainersResource extends DefaultResource[DemoApplication, DockerContainer, DockerContainerList] {

  override def getList(re: RequestEvent) = DockerContainerList(repo.find())

  override def repo: RepositoryApi[DockerContainer] = getApplication().dockerRepo

  override def getTemplate(re: RequestEvent) = null//DockerContainer(None, "test")

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/DockerContainers")

  override def updateEntity(re: RequestEvent)(implicit system: ActorSystem): Unit = {
    val updatedEntity = re.cmd.entity.asInstanceOf[DockerContainer]
    val entityToSave = updatedEntity.copy(id = Some(re.firstParam()))
    repo.save(entityToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = null
//      DockerContainer(None,
//        map.getOrElse("name", "Unknown")
//        //map.getOrElse("url", ""),
//        //List()
//      )
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}


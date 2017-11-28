package io.skysail.server.demo.services

import akka.actor.ActorSystem
import io.skysail.server.actors.ApplicationActor
import io.skysail.server.app.SkysailApplication
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark
import akka.pattern.ask

import scala.concurrent.Future

class BookmarksService {

//  def getBookmarks(system: ActorSystem) = {
//    val applicationActor = SkysailApplication.getApplicationActorSelection(system, classOf[DemoApplication].getName)
//    val r: Future[DemoApplication] = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
//  }


  def createApplication(app: Bookmark): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Bookmark]] = ???


}

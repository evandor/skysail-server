package io.skysail.server.app.appboard

import io.skysail.domain.RequestEvent
import io.skysail.domain.app.{Application, ApplicationList}
import io.skysail.domain.resources.EntityResource
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

class AppsResource() extends EntityResource[AppboardApplication, ApplicationList] {

  private val log = LoggerFactory.getLogger(this.getClass)

//  def getAsync(requestEvent: RequestEvent): Unit = {
//    val appService = getApplication().appService
//    val apps: Future[List[Application]] = appService.getAllApplications(this.actorContext.system)
//    apps.onComplete {
//      case Success(s) => requestEvent.controllerActor ! ResponseEvent(requestEvent,s)
//      case Failure(f) => log error s"failure $f"; null
//    }
//  }

  override def getEntity(re: RequestEvent): Option[ApplicationList] = {
    val appService = getApplication().appService
    val apps: Future[List[Application]] = appService.getAllApplications(this.actorContext.system)

    val r = Await.result(apps, 1.seconds)
    Some(ApplicationList(r))
  }

}
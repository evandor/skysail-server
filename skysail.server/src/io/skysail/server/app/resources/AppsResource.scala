package io.skysail.server.app.resources

import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.app.ApplicationList
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.RootApplication
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

case class Application(name: String, context: String, description: String)

class AppsResource() extends EntityResource[RootApplication, ApplicationList] {

  private val log = LoggerFactory.getLogger(this.getClass)

//  def getAsync(requestEvent: RequestEvent): Unit = {
//    val appService = getApplication().appService
//    val apps = appService.getAllApplications(this.actorContext.system)
//    apps.onComplete {
//      case Success(s) => requestEvent.controllerActor ! ResponseEvent(requestEvent,s)
//      case Failure(f) => log error s"failure $f"; null
//    }
//  }

  override def getEntity(requestEvent: RequestEvent): Option[ApplicationList] = {
    val appService = getApplication().appService
    val apps = appService.getAllApplications(this.actorContext.system)

    val r = Await.result(apps, 1.seconds)
    Some(ApplicationList(r))
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {null}


}
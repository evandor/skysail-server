package io.skysail.server.app.appboard

import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEvent, ResponseEventBase}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

case class Application(name: String, context: String, description: String)

class AppsResource() extends EntityResource[AppboardApplication, Application] {

  private val log = LoggerFactory.getLogger(this.getClass)

  def getAsync(requestEvent: RequestEvent): Unit = {
    val appService = getApplication().appService
    val apps: Future[List[Application]] = appService.getAllApplications(this.actorContext.system)
    apps.onComplete {
      case Success(s) => requestEvent.controllerActor ! ResponseEvent(requestEvent,s)
      case Failure(f) => log error s"failure $f"; null
    }
  }

//  override def get(requestEvent: RequestEvent):AsyncResponseEvent = {
//    getAsync(requestEvent)
//    AsyncResponseEvent(requestEvent)
//  }
  override def getEntity(re: RequestEvent): Option[Application] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
}
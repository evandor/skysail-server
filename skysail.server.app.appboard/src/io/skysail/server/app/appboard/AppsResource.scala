package io.skysail.server.app.appboard

import io.skysail.domain.{AsyncResponseEvent, ListResponseEvent, RequestEvent}
import io.skysail.domain.resources.ListResource
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

case class Application(name: String, context: String, description: String)

class AppsResource() extends ListResource[AppboardApplication, Application] {

  private val log = LoggerFactory.getLogger(this.getClass)

  def getAsync(requestEvent: RequestEvent): Unit = {
    val appService = getApplication().appService
    val apps = appService.getAllApplications(this.actorContext.system)
    apps.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent,s)
      case Failure(f) => log error s"failure $f"; null
    }
  }

  override def get(requestEvent: RequestEvent):AsyncResponseEvent = {
    getAsync(requestEvent)
    AsyncResponseEvent(requestEvent)
  }
}
package io.skysail.server.app.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource
import io.skysail.server.app.RootApplication
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

case class Application(name: String, context: String, description: String)

class AppsResource() extends ListResource[Application] {

  private val log = LoggerFactory.getLogger(this.getClass)

  def get(requestEvent: RequestEvent) {
    val appService = application.asInstanceOf[RootApplication].appService
    val apps = appService.getAllApplications(this.actorContext.system)
    apps.onComplete {
      case Success(s) => requestEvent.controllerActor ! s
      case Failure(f) => log error s"failure $f"
    }

  }

}
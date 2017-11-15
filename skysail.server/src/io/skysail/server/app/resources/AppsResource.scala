package io.skysail.server.app.resources

import io.skysail.api.ddd.DddElement
import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncListResource
import io.skysail.server.actors.ApplicationsActor
import io.skysail.server.app.SkysailApplication
import org.slf4j.LoggerFactory
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

case class Application(name: String, context: String, description: String)

class AppsResource() extends AsyncListResource[Application] {

  private val log = LoggerFactory.getLogger(this.getClass())

  def get(requestEvent: RequestEvent): Unit = {

    val appsActor = SkysailApplication.getApplicationsActor(this.actorContext.system)

    (appsActor ? ApplicationsActor.GetAllApplications())
      .mapTo[List[Application]]
      .onComplete {
        case Success(s) => requestEvent.controllerActor ! s
        case Failure(f) => log error s"failure ${f}"
      }

  }

}
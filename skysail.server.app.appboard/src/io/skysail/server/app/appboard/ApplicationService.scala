package io.skysail.server.app.appboard

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.api.ui.Client
import io.skysail.domain.app.Application
import io.skysail.server.actors.ApplicationsActor

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class ApplicationService() {

  implicit val timeout: Timeout = 3.seconds

  var clients =  List[Client]()

  def getAllApplications(system: ActorSystem): Future[List[Application]] = {
    val appsActor = AppboardApplication.getApplicationsActor(system)
    (appsActor ? ApplicationsActor.GetAllApplications()).mapTo[List[Application]]
  }

}

package io.skysail.server.app

import akka.actor.ActorSystem
import io.skysail.server.actors.ApplicationsActor

trait SkysailApplication {
  def getApplicationsActor(system: ActorSystem): ApplicationsActor
}
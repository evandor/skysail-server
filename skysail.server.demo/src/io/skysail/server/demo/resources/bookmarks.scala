package io.skysail.server.demo.resources

import akka.pattern.ask
import io.skysail.domain.resources.AsyncListResource
import io.skysail.domain.{ListResponseEvent, RequestEvent}
import io.skysail.server.actors.ApplicationActor
import io.skysail.server.app.SkysailApplication
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class BookmarksResource extends AsyncListResource[Bookmark] {

  //val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    //reply(requestEvent, app.repo.find())
    r onComplete {
      case Success(app) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, app.repo.find())
      case Failure(failure) => println(failure)
    }

  }
}
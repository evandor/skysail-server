//package io.skysail.server.app
//
//import akka.actor.ActorSystem
//import akka.pattern.ask
//import akka.util.Timeout
//import io.skysail.domain.app.Menu
//import io.skysail.server.actors.ApplicationsActor
//
//import scala.concurrent.Future
//import scala.concurrent.duration.DurationInt
//
//class MenuService() {
//
//  implicit val timeout: Timeout = 3.seconds
//
//  def getAllMenus(system: ActorSystem): Future[List[Menu]] = {
//    val appsActor = BackendApplication.getApplicationsActor(system)
//    val r: Future[List[Menu]] = (appsActor ? ApplicationsActor.GetAllApplications())
//      .mapTo[List[Menu]]
//
//
//    r
//  }
//
//}

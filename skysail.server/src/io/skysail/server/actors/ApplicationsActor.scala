package io.skysail.server.actors

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Props
import akka.http.scaladsl.model.HttpResponse
import akka.pattern.ask
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.ActorRef
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.actors.ApplicationsActor._
import scala.concurrent.Future
import scala.util.{ Success, Failure }
import akka.event.LoggingReceive
import io.skysail.domain.app.Application
import io.skysail.server.app.SkysailApplication._

object ApplicationsActor {
  case class GetAllApplications()
  case class GetMenus()
}

class ApplicationsActor extends Actor with ActorLogging {

  val cnt = new AtomicInteger(0)

  implicit val timeout = Timeout(5.seconds)

  val appActors = scala.collection.mutable.Map[String, ActorRef]()

  def receive = LoggingReceive {
    case rac: InitResourceActorChain => handleInitResourceActorChain(rac)
    case caa: CreateApplicationActor => createApplicationActor(caa)
    case daa: DeleteApplicationActor => deleteApplicationActor(daa)
    case gaa: GetAllApplications => getAllApplications(gaa)
    //case _: GetMenus => getMenus()
    case msg: Any => log info s"received unknown message '$msg' of type '${msg.getClass().getName}' in ${this.getClass.getName}"
  }

  private def handleInitResourceActorChain(rac: InitResourceActorChain) = {
    //implicit val askTimeout: Timeout = 3.seconds

    val actor = context.actorOf(Props.apply(rac.cls), rac.cls.getSimpleName + "-" + cnt.incrementAndGet())
    //println("PATH: " + actor.path)
    onSuccess((actor ? rac.requestContext).mapTo[HttpResponse]) { result =>
      val r = complete(result)
      //system.stop(actor)
      r
    }
  }

  private def createApplicationActor(caa: CreateApplicationActor) = {
    log info s"creating ApplicationActor ${caa.cls.getName}..."
    val a = context.actorOf(Props.apply(classOf[ApplicationActor], caa.appModel, caa.application, caa.bundleContext), caa.cls.getName)
    appActors += caa.cls.getName -> a
    log info s"added new ${caa.cls.getName} actor to applicationsActors Map, size is now ${appActors.size}"
    a
  }

  private def deleteApplicationActor(daa: DeleteApplicationActor) = {
    log info s"deleting ApplicationActor ${daa.cls.getName}..."
    val actor = appActors.remove(daa.cls.getName)
    log info s"deleted ${daa.cls.getName} actor from applicationsActors Map, size is now ${appActors.size}"
    log info s"stopping ${daa.cls.getName} actor"
    actor.map(context.stop(_))
  }

  private def getAllApplications(gaa: GetAllApplications) = {
    val originalSender = sender
    val p = context.children.map(appActor => {
      (appActor ? ApplicationActor.GetAppModel()).mapTo[ApplicationModel]
    }).toList
    implicit val ec = context.system.dispatcher
    Future.sequence(p) onComplete {
      case Success(result) => originalSender ! result.map(appModel => Application(appModel)).toList
      case Failure(failure) => log error s"Failure: ${failure}"
    }
  }

//  private def getMenus() = {
//    val originalSender = sender
//    val p = context.children.map(appActor => {
//      (appActor ? ApplicationActor.GetMenu()).mapTo[Option[MenuItem]]
//    }).toList
//    implicit val ec = context.system.dispatcher
//    Future.sequence(p) onComplete {
//      case Success(result) => originalSender ! result.filter(m => m.isDefined).toList
//      case Failure(failure) => log error s"Failure: ${failure}"
//    }
//  }
}
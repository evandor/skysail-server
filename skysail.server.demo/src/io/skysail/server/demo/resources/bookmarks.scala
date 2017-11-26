package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.pattern.ask
import io.skysail.domain.resources.{ListResource, PostResource, PutResource, AsyncResource}
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEvent}
import io.skysail.server.actors.ApplicationActor
import io.skysail.server.app.SkysailApplication
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import spray.json.{DefaultJsonProtocol, _}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import io.skysail.domain.messages.ProcessCommand

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val bookmarkFormat = jsonFormat3(Bookmark)
}

class BookmarksResource extends ListResource[Bookmark] {

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

class PostBookmarkResource extends PostResource[Bookmark] with JsonSupport {

  def get(requestEvent: RequestEvent): Unit = {
    val entityModel = applicationModel.entityModelFor(classOf[Bookmark])
    if (entityModel.isDefined) {
      requestEvent.controllerActor ! entityModel.get.description()
    } else {
      requestEvent.controllerActor ! ResponseEvent(requestEvent, Bookmark(None, "", "")) //describe(classOf[Contact])
    }
  }

  def post(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    r onComplete {
      case Success(app) => app.repo.save(requestEvent.cmd.entity)
      case Failure(failure) => println(failure)
    }
    requestEvent.controllerActor ! Bookmark(null, "a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {

    implicit val materializer = ActorMaterializer()

    val a = Unmarshaller.stringUnmarshaller
      .forContentTypes(ContentTypes.`application/json`)
      .map(_.parseJson.convertTo[Bookmark])

    val entity1 = processCommand.ctx.request.entity
    println ("Entity1" + entity1)
    val b = a.apply(entity1)
    println ("Entity2" + b)

    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}

class PutBookmarkResource extends PutResource[Bookmark] with JsonSupport {

  override def get(requestEvent: RequestEvent): Unit = {
    val id = requestEvent.cmd.urlParameter.head

    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    r onSuccess {
      case app =>
        val optionalBookmark = app.repo.find(id)
        requestEvent.controllerActor ! ResponseEvent(requestEvent, optionalBookmark.get)
    }
  }

  override def put(requestEvent: RequestEvent): Unit = {
    val applicationActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (applicationActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    r onSuccess {
      case app => app.repo.save(requestEvent.cmd.entity)
    }
    requestEvent.controllerActor ! requestEvent.cmd.entity

  }
}

class BookmarkResource extends AsyncResource[Bookmark] {
  override def get(requestEvent: RequestEvent): Unit = {
    println("hier")
  }
}
package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{AsyncResource, ListResource, PostResource, PutResource}
import io.skysail.domain.{ListResponseEvent, RequestEvent, ResponseEvent}
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark
import spray.json.{DefaultJsonProtocol, _}

import scala.concurrent.ExecutionContext.Implicits.global

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val bookmarkFormat = jsonFormat3(Bookmark)
}

class BookmarksResource extends ListResource[DemoApplication, Bookmark] {
  def get(requestEvent: RequestEvent) = ListResponseEvent(requestEvent, getApplication().repo.find())
}

class PostBookmarkResource extends PostResource[DemoApplication, Bookmark] with JsonSupport {

  def get(requestEvent: RequestEvent) = {
//    val entityModel = applicationModel.entityModelFor(classOf[Bookmark])
//    if (entityModel.isDefined) {
//      requestEvent.controllerActor ! entityModel.get.description()
//    }
    ResponseEvent(requestEvent, Bookmark(None, "", ""))
  }

  def post(requestEvent: RequestEvent) {
    getApplication().repo.save(requestEvent.cmd.entity)
    requestEvent.controllerActor ! Bookmark(null, "a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {

    implicit val materializer = ActorMaterializer()

    val a = Unmarshaller.stringUnmarshaller
      .forContentTypes(ContentTypes.`application/json`)
      .map(_.parseJson.convertTo[Bookmark])

    val entity1 = processCommand.ctx.request.entity
    println("Entity1" + entity1)
    val b = a.apply(entity1)
    println("Entity2" + b)

    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}

class PutBookmarkResource extends PutResource[DemoApplication, Bookmark] with JsonSupport {

  override def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    val id = requestEvent.cmd.urlParameter.head
    val optionalBookmark = getApplication().repo.find(id)
    ResponseEvent(requestEvent, optionalBookmark.get)
  }

  override def put(requestEvent: RequestEvent): Unit = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    // merge
    getApplication().repo.save(optionalBookmark.get)
  }
}

class BookmarkResource extends AsyncResource[DemoApplication, Bookmark] {
  override def get(requestEvent: RequestEvent) = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalBookmark.get)
  }
}
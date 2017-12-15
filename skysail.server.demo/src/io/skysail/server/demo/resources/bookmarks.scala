package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{AsyncResource, ListResource, PostResource, PutResource}
import io.skysail.domain.{RequestEvent, ResponseEvent}
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark
import spray.json.{DefaultJsonProtocol, _}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val bookmarkFormat: RootJsonFormat[Bookmark] = jsonFormat3(Bookmark)
}

class BookmarksResource extends ListResource[DemoApplication, Bookmark] {
  override def getList(re: RequestEvent): List[Bookmark] = getApplication().repo.find()
}

class PostBookmarkResource extends PostResource[DemoApplication, Bookmark] with JsonSupport {

  def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    //      requestEvent.controllerActor ! applicationModel.entityModelFor(classOf[Bookmark]).get.description()
    ResponseEvent(requestEvent, Bookmark(None, "", ""))
  }

  def post(requestEvent: RequestEvent) {
    val b = getApplication().repo.save(requestEvent.cmd.entity)
    requestEvent.controllerActor ! Bookmark(Some(b), "a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}

class PutBookmarkResource extends PutResource[DemoApplication, Bookmark] with JsonSupport {

  override def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalBookmark.get)
  }

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    val updatedBookmark = requestEvent.cmd.entity.asInstanceOf[Bookmark]
    val bookmarkToSave = updatedBookmark.copy(id = optionalBookmark.get.id)
    getApplication().repo.save(bookmarkToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
}

class BookmarkResource extends AsyncResource[DemoApplication, Bookmark] {
  override def get(requestEvent: RequestEvent) = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalBookmark.get)
  }
}
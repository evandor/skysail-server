package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{AsyncResource, ListResource, PostResource, PutResource}
import io.skysail.domain.{RequestEvent, ResponseEvent}
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark
import spray.json.{DefaultJsonProtocol, _}

import scala.concurrent.ExecutionContext.Implicits.global

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
    getApplication().repo.save(requestEvent.cmd.entity)
    requestEvent.controllerActor ! Bookmark(null, "a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {

//    implicit val materializer = ActorMaterializer()
//
//    val a: Unmarshaller[HttpEntity, Bookmark] = Unmarshaller.stringUnmarshaller
//      .forContentTypes(ContentTypes.`application/json`)
//      .map(_.parseJson.convertTo[Bookmark])
//
//    val entity1 = processCommand.ctx.request.entity
//    println("dort: " + entity1)
//    val b = a.apply(entity1)
//    println("dort: " + b)

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

    val e = requestEvent.cmd.ctx.request.entity
    println("hier: " + e)

    implicit val materializer = ActorMaterializer()

    //FromStringUnmarshaller[Bookmark]
    val p1 = Unmarshaller.stringUnmarshaller.map(m => println("Hier1 " + s"$m"))
    val p2 = p1.apply(e)
    println("hier2:" + p2)

    val a: Unmarshaller[HttpEntity, Bookmark] = Unmarshaller.stringUnmarshaller
     // .forContentTypes(ContentTypes.)
      .map(_.parseJson.convertTo[Bookmark])

    val b = a.apply(e)

    println("hier3:" + b)

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
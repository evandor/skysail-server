package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ ActorSelection, ActorSystem }
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{ EntityResource, PostResource, PutResource }
import io.skysail.domain.{ RedirectResponseEvent, RequestEvent, ResponseEvent, ResponseEventBase }
import io.skysail.server.adapter.JSoupAdapter
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{ Bookmark, BookmarkList }
import io.skysail.server.demo.services.BookmarksService
import org.jsoup.nodes.Document

import scala.util.{ Failure, Success, Try }
import scala.util.matching.Regex

class BookmarksResource extends EntityResource[DemoApplication, BookmarkList] {
  override def getEntity(re: RequestEvent): Option[BookmarkList] = Some(BookmarkList(getApplication().repo.find()))

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {
    RedirectResponseEvent(requestEvent, "", Some(".."))
  }
}

class PostBookmarkResource extends PostResource[DemoApplication, Bookmark] {

  def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    ResponseEvent(requestEvent, Bookmark(None, "", ""))
  }

  def post(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = {
    var bookmark = requestEvent.cmd.entity.asInstanceOf[Bookmark]
    val bmWithMetadata = BookmarksService.addMetadata(bookmark)
    val b = getApplication().repo.save(bmWithMetadata)
    getApplication().eventService.send("bookmark created")
    val redirectTo = Some("/demo/v1/bms")
    val newRequest = requestEvent.cmd.ctx.request.copy(method = HttpMethods.GET)
    RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

}

class PutBookmarkResource extends PutResource[DemoApplication, Bookmark] {

  override def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalBookmark.get)
  }

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    val updatedBookmark = requestEvent.cmd.entity.asInstanceOf[Bookmark]
    val bookmarkToSave = updatedBookmark.copy(id = optionalBookmark.get.id)
    getApplication().repo.save(bookmarkToSave)
    val redirectTo = Some("/demo/v1/bms")
    RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {
    //val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    //if (optionalBookmark.isDefined) {
    getApplication().repo.delete(requestEvent.cmd.urlParameter.head)
    //}
    RedirectResponseEvent(requestEvent, "", Some("/demo/v1/bms"))
  }

}

class BookmarkResource extends EntityResource[DemoApplication, Bookmark] {

  //override def get(requestEvent: RequestEvent) = {
  override def getEntity(re: RequestEvent): Option[Bookmark] = {
    val app: DemoApplication = getApplication()
    val optionalBookmark = app.repo.find(re.cmd.urlParameter.head)
    val bm = optionalBookmark.getOrElse(Bookmark(None, "undef", "undef"))
    if (bm.url.contains("$")) {
      val pattern = new Regex("\\$\\{(.*?)}")
      val matchList = (pattern findAllIn bm.url).toList
      println((pattern findAllIn bm.url).mkString(","))
      val hits = matchList.map(hit => hit.substring(2, hit.length - 1)).map(hit => hit -> app.getList(hit))
      println(hits)
      val variant1 = new Bookmark(None, "var", "1")

      val lists = hits
        .map(hit => hit._2.map(sub => bm.url.replace("${" + hit._1 + "}", sub)))
        .flatten.toList

      val variants = lists.map(l => Bookmark(None, "-", l)).toList

      val bmWithVariants = new Bookmark(bm.id, "*" + bm.title + "*", bm.url /*, variants*/ )

      //ResponseEvent(requestEvent, bmWithVariants)
      Some(bmWithVariants)
    } else {
      //ResponseEvent(requestEvent, bm)
      Some(bm)
    }
  }

}
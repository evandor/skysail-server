package io.skysail.server.app

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives.formFieldMap
import akka.http.scaladsl.server.PathMatchers._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable, TextLink}
import io.skysail.domain.app.ApiVersion
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{EntityResource, PostResource}
import io.skysail.domain.routes.RouteMapping
import io.skysail.domain.{RequestEvent, ResponseEvent, ResponseEventBase}
import io.skysail.server.routes.RoutesCreator


case class Bookmark(id: Option[String], title: String, url: String) extends Entity[String] with Linkable {
  override def _links = List(TextLink("self", "hier",""))
}

class BookmarksResource extends EntityResource[TestApp, Bookmark] {
  //override def getList(re: RequestEvent): List[Bookmark] = ??? //getApplication().repo.find()
  override def getEntity(re: RequestEvent): Option[Bookmark] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  override def put(requestEvent: RequestEvent): ResponseEventBase = { null }

}

class PostBookmarkResource extends PostResource[TestApp, Bookmark] /*with JsonSupport */ {

  def get(requestEvent: RequestEvent) = ResponseEvent(requestEvent, Bookmark(None, "", ""))

  def post(requestEvent: RequestEvent) {
    //val b = getApplication().repo.save(requestEvent.cmd.entity)
    requestEvent.controllerActor ! Bookmark(Some(""), "a@b.com", "Mira")
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  override def put(requestEvent: RequestEvent): ResponseEventBase = { null }

}

class TestApp extends BackendApplication(null, routesCreator = new RoutesCreator(null), null) {
  override def routesMappings: List[RouteMapping[_, _]] = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("/bms", root / PathMatcher("bms") ~ PathEnd, classOf[BookmarksResource]),
      RouteMapping("/bms/", root / PathMatcher("bms") / PathEnd, classOf[PostBookmarkResource])
    )
  }

  override def name = "Testapp"
  override def desc = "Testapp"
  override def version = ApiVersion(2)
}

//@RunWith(classOf[JUnitRunner])
//class BackendApplicationTest extends FlatSpecLike with Matchers with BeforeAndAfterAll {
//
//  "a SkysailApplication" should "b" in {
//    val app = new TestApp
//    val router: Route = app.router
//    println (router)
//  }
//
//}


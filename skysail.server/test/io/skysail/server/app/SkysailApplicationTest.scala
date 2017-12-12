package io.skysail.server.app

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives.formFieldMap
import akka.http.scaladsl.server.PathMatchers._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable}
import io.skysail.domain.app.ApiVersion
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{ListResource, PostResource}
import io.skysail.domain.routes.RouteMapping
import io.skysail.domain.{RequestEvent, ResponseEvent}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}


case class Bookmark(id: Option[String], title: String, url: String) extends Entity[String] with Linkable {
  override def _links = List(Link("self", "hier"))
}

class BookmarksResource extends ListResource[TestApp, Bookmark] {
  override def getList(re: RequestEvent): List[Bookmark] = ??? //getApplication().repo.find()
}

class PostBookmarkResource extends PostResource[TestApp, Bookmark] /*with JsonSupport */ {

  def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    ResponseEvent(requestEvent, Bookmark(None, "", ""))
  }

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
}

class TestApp extends SkysailApplication("testapp", ApiVersion(2), null, null, "desc") {
  override def routesMappings: List[RouteMapping[_, _]] = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("/bms", root / PathMatcher("bms") ~ PathEnd, classOf[BookmarksResource]),
      RouteMapping("/bms/", root / PathMatcher("bms") / PathEnd, classOf[PostBookmarkResource])
    )
  }
}

class SkysailApplicationTest extends FlatSpecLike with Matchers with BeforeAndAfterAll {

  "a SkysailApplication" should "b" in {
    val app = new TestApp
    val router: Route = app.router
  }

}


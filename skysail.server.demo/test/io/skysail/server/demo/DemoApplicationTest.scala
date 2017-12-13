package io.skysail.server.demo

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.MediaRange.One
import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.headers.Accept
import akka.http.scaladsl.server.{Directive1, Route}
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import io.skysail.api.persistence.DbService
import io.skysail.api.security.AuthenticationService
import io.skysail.server.Constants
import io.skysail.server.actors.ApplicationsActor
import io.skysail.server.app.BackendApplication
import io.skysail.server.routes.RoutesCreator
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.osgi.framework.BundleContext
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration.DurationInt

@RunWith(classOf[JUnitRunner])
class DemoApplicationTest() extends WordSpec with Matchers with ScalatestRouteTest {

  // https://stackoverflow.com/questions/32214005/request-was-neither-completed-nor-rejected-within-1-second-scala-spray-testing
  implicit def default(implicit system: ActorSystem) = RouteTestTimeout(5.seconds)

  //val dbService = Mockito.mock(classOf[DbService])

  val dbService = new DbService() {
    override def createWithSuperClass(superClass: String, vertices: String*): Unit = {}

    override def register(classes: Class[_]*): Unit = {}

    override def persist(entity: Any): String = {
      ""
    }

    override def findGraphs[T: Manifest](cls: Class[T], sql: String): List[T] = List()
  }

  val bundleContext = Mockito.mock(classOf[BundleContext])
  val app = new DemoApplication(null, dbService, null)
  val routesCreator = RoutesCreator(system)
  val applicationsActor = system.actorOf(Props[ApplicationsActor], Constants.APPLICATIONS_ACTOR_NAME)

  val acceptHeader = Accept(One(MediaTypes.`application/json`, 1.0f))

  applicationsActor ! BackendApplication.CreateApplicationActor(
    classOf[DemoApplication], app.appModel, app, bundleContext)

  //val tmp: List[Route] = app.routes.map { prt => routesCreator.createRoute(prt, app) }
  //val appRoutes: scala.collection.mutable.ListBuffer[Route] = scala.collection.mutable.ListBuffer(tmp: _*)
  if (app.nativeRoute().isDefined) {
    //appRoutes ++= app.nativeRoute.get  // TODO
  }
  //val routes: List[Route] = appRoutes.toList
  //val res: Route = routes.reduce((a, b) => a ~ b)
  val res: Route = app.router

  routesCreator.authentication = new AuthenticationService() {
    override def directive() = new Directive1[String]() {
      override def tapply(f: Tuple1[String] => Route) = {
        val t = Tuple1("tttxxx")
        f(t)
      }
    }
  }

  "A GET request to /demo/v1/bms" should {

    "return the html page if no accept header was set" in {
      Get("/demo/v1/bms") ~> res ~> check {
        status shouldBe OK
        contentType shouldBe `text/html(UTF-8)`
        responseAs[String] should include("Create New Bookmark")
      }
    }

    "return the json representation if an accept header for application/json is sent" in {
      Get("/demo/v1/bms").addHeader(acceptHeader) ~> res ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
        responseAs[String] should include("[]")
      }
    }
  }

  //  "A POST request to /demo/v1/bms" should {
  //
  //    "return the html page if no accept header was set" in {
  //      Post("/demo/v1/bms") ~> res ~> check {
  //        status shouldBe OK
  //        contentType shouldBe `text/html(UTF-8)`
  //        responseAs[String] should include("Create New Bookmark")
  //      }
  //    }
  //  }


  "A GET request to /demo/v1/bms/" should {

    "return the html page if no accept header was set" in {
      Get("/demo/v1/bms/") ~> res ~> check {
        status shouldBe OK
        contentType shouldBe `text/html(UTF-8)`
        responseAs[String] should include("submit")
      }
    }

    "return the json representation if an accept header for application/json is sent" in {
      Get("/demo/v1/bms/").addHeader(acceptHeader) ~> res ~> check {
        status shouldBe OK
        //contentType shouldBe `application/json`
        responseAs[String] should include("{\"title\":\"\",\"url\":\"\"}")
      }
    }
  }

  "A POST request to /demo/v1/bms/" should {

    "return the html page if no accept header was set" in {
      val e = akka.http.scaladsl.model.FormData(Map("title" -> "t", "url" -> "http://url")).toEntity
      Post("/demo/v1/bms/").withEntity(e) ~> res ~> check {
        status shouldBe OK
        //contentType shouldBe `text/html(UTF-8)`
        responseAs[String] should include("{\"id\":\"\",\"title\":\"a@b.com\",\"url\":\"Mira\"}")
      }
    }

  }

}

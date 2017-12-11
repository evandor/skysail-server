package io.skysail.server.demo

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.{Directive1, Route}
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import io.skysail.api.persistence.DbService
import io.skysail.api.security.AuthenticationService
import io.skysail.server.Constants
import io.skysail.server.actors.ApplicationsActor
import io.skysail.server.app.SkysailApplication
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

  val dbService = Mockito.mock(classOf[DbService])
  val bundleContext = Mockito.mock(classOf[BundleContext])
  val app = new DemoApplication(null, dbService)
  val routesCreator = RoutesCreator(system)
  val applicationsActor = system.actorOf(Props[ApplicationsActor], Constants.APPLICATIONS_ACTOR_NAME)

  applicationsActor ! SkysailApplication.CreateApplicationActor(
    classOf[DemoApplication], app.appModel, app, bundleContext)

  routesCreator.authentication = new AuthenticationService() {
    override def directive() = new Directive1[String]() {
      override def tapply(f: Tuple1[String] => Route) = {
        val t = Tuple1("tttxxx")
        f(t)
      }
    }
  }


  "The service" should {

    "return the html page for a GET request to the /bms path" in {
      val listResourceMapping = app.routesMappings.filter(m => m.path == "/bms").head
      val r: Route = routesCreator.createRoute(listResourceMapping, app)
      Get("/demo/v1/bms") ~> r ~> check {
        responseAs[String] should include ("Create New Bookmark")
      }
    }

    "return the json representation for a GET request to the /bms path with content-type json" in {
      val listResourceMapping = app.routesMappings.filter(m => m.path == "/bms").head
      val r: Route = routesCreator.createRoute(listResourceMapping, app)
      Get("/demo/v1/bms").addHeader(RawHeader("Accept", "application/json")) ~> r ~> check {
        responseAs[String] should include ("Create New Bookmark1")
      }
    }

  }
}

package io.skysail.server.demo

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.MediaRange.One
import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.headers.Accept
import akka.http.scaladsl.server.{Directive1, Route}
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import io.skysail.api.security.AuthenticationService
import io.skysail.server.Constants
import io.skysail.server.actors.ApplicationsActor
import io.skysail.server.app.BackendApplication
import io.skysail.server.routes.RoutesCreator
import org.json4s.DefaultFormats
import org.mockito.Mockito
import org.osgi.framework.BundleContext
import org.scalatest.{Matchers, WordSpec}
import scala.concurrent.duration.DurationInt

class DemoApplicationTest() extends WordSpec with Matchers with ScalatestRouteTest {

  // https://stackoverflow.com/questions/32214005/request-was-neither-completed-nor-rejected-within-1-second-scala-spray-testing
  implicit def default(implicit system: ActorSystem) = RouteTestTimeout(5.seconds)

  implicit val formats = DefaultFormats

  val dbService = new DummyDbService()
  val bundleContext = Mockito.mock(classOf[BundleContext])
  val routesCreator = RoutesCreator(system)
  val app = new DemoApplication(null, dbService, null, routesCreator)
  val applicationsActor = system.actorOf(Props[ApplicationsActor], Constants.APPLICATIONS_ACTOR_NAME)
  val applicationJsonAcceptHeader = Accept(One(MediaTypes.`application/json`, 1.0f))

  applicationsActor ! BackendApplication.CreateApplicationActor(
    classOf[DemoApplication], app.appModel, app, bundleContext)

  if (app.optionalNativeRoute().isDefined) {
    //appRoutes ++= app.nativeRoute.get  // TODO
  }
  val router: Route = app.router

  routesCreator.authentication = new AuthenticationService() {
    override def directive() = new Directive1[String]() {
      override def tapply(f: Tuple1[String] => Route) = {
        val t = Tuple1("tttxxx")
        f(t)
      }
    }
  }


}

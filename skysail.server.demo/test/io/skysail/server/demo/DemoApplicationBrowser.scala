//package io.skysail.server.demo
//
//import akka.actor.{ActorSystem, Props}
//import akka.http.scaladsl.model.ContentTypes._
//import akka.http.scaladsl.model.MediaRange.One
//import akka.http.scaladsl.model.MediaTypes
//import akka.http.scaladsl.model.StatusCodes._
//import akka.http.scaladsl.model.headers.Accept
//import akka.http.scaladsl.server.{Directive1, Route}
//import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
//import io.skysail.api.security.AuthenticationService
//import io.skysail.server.Constants
//import io.skysail.server.actors.ApplicationsActor
//import io.skysail.server.app.BackendApplication
//import io.skysail.server.routes.RoutesCreator
//import org.junit.runner.RunWith
//import org.mockito.Mockito
//import org.osgi.framework.BundleContext
//import org.scalatest.junit.JUnitRunner
//import org.scalatest.{Matchers, WordSpec}
//
//import scala.concurrent.duration.DurationInt
//
//class DemoApplicationBrowser(router: Route) extends ScalatestRouteTest {
//
//  def getList() = {
//    Get("/demo/v1/bms") ~> router
//  }
//
//}

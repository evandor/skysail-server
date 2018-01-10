package io.skysail.server.app

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{get, getFromResourceDirectory, pathPrefix}
import akka.http.scaladsl.server.PathMatchers._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.api.ui.Client
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.app.resources.{AppsResource, ClientsResource, RootResource}
import io.skysail.server.routes.RoutesCreator
import org.osgi.framework.BundleContext

import scala.concurrent.ExecutionContextExecutor

object RootApplication {
  //val LOGIN_PATH = "/_login"
  //val LOGIN_CALLBACK = "/_logincallback"
  //val PROFILE_PATH = "/_profile"
  //val PUPLIC_PATH = "/_public"
  //val LOGOUT_PATH = "/_logout"

  val CONFIG_IDENTIFIER_LANDINGPAGE_NOT_AUTHENTICATED = "landingPage.notAuthenticated"
  val CONFIG_IDENTIFIER_LANDINGPAGE_AUTHENTICATED = "landingPage.authenticated"

}

class RootApplication(
                       bundleContext: BundleContext,
                       routesCreator: RoutesCreator,
                       system: ActorSystem,
                       val conf: Map[String, Any]) extends BackendApplication(bundleContext, routesCreator, null)
  with ApplicationProvider {

  override def name = "_root"
  override def desc = "backend root"
  override def version = null


  var clients = List[Client]()

  val appService = new ApplicationService()

  def setClients(clients: List[Client]) = appService.clients = clients

  def routesMappings: List[RouteMapping[_, _]] = {
    val root: PathMatcher[Unit] = PathMatcher(name)

    List(
      //"/login" -> classOf[AkkaLoginResource[String]],
      //RouteMapping("", PathMatchers.PathEnd, classOf[RootRedirectResource]),
      RouteMapping("", root ~ PathEnd, classOf[RootResource]),
      RouteMapping("/apps", root / PathMatcher("apps") ~ PathEnd, classOf[AppsResource]),
      RouteMapping("/clients", root / PathMatcher("clients") ~ PathEnd, classOf[ClientsResource])
    )
  }

  override def nativeRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("") {
      get {
        getFromResourceDirectory("assets", this.getClass.getClassLoader)
      }
    }
  }

}
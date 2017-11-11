package io.skysail.server.app

import io.skysail.domain.routes.RouteMapping
import akka.http.scaladsl.server.PathMatcher
import io.skysail.server.app.RootApplication._
import io.skysail.server.app.resources.RootResource

object RootApplication {
  val ROOT_APPLICATION_NAME = "root"

  //val LOGIN_PATH = "/_login"
  //val LOGIN_CALLBACK = "/_logincallback"
  //val PROFILE_PATH = "/_profile"
  //val PUPLIC_PATH = "/_public"
  //val LOGOUT_PATH = "/_logout"

  val CONFIG_IDENTIFIER_LANDINGPAGE_NOT_AUTHENTICATED = "landingPage.notAuthenticated"
  val CONFIG_IDENTIFIER_LANDINGPAGE_AUTHENTICATED = "landingPage.authenticated"

}
class RootApplication extends SkysailApplication(ROOT_APPLICATION_NAME, null, null, "backend root")
  with ApplicationProvider {

  def routesMappings: List[RouteMapping[_]] = {
    val root: PathMatcher[Unit] = PathMatcher("root")

    List(
      //"/login" -> classOf[AkkaLoginResource[String]],
      RouteMapping("", root, classOf[RootResource])
    )
  }
}
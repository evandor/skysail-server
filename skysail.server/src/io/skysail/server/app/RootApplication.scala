package io.skysail.server.app

import io.skysail.domain.routes.RouteMapping
import akka.http.scaladsl.server.{PathMatcher, PathMatchers}
import io.skysail.server.app.RootApplication._
import io.skysail.server.app.resources.{AppsResource, RootRedirectResource, RootResource}
import org.osgi.framework.BundleContext

object RootApplication {
  val ROOT_APPLICATION_NAME = "_root"

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
                       val conf: Map[String, Any]) extends SkysailApplication(ROOT_APPLICATION_NAME, null, bundleContext, "backend root")
  with ApplicationProvider {

  val appService = new ApplicationService()

  def routesMappings: List[RouteMapping[_,_]] = {
    val root: PathMatcher[Unit] = PathMatcher(ROOT_APPLICATION_NAME)

    List(
      //"/login" -> classOf[AkkaLoginResource[String]],
      RouteMapping("", PathMatchers.PathEnd, classOf[RootRedirectResource]),
      RouteMapping("", root ~ PathMatchers.PathEnd, classOf[RootResource]),
      RouteMapping("/apps", root / PathMatcher("apps") ~ PathMatchers.PathEnd, classOf[AppsResource])
    )
  }



}
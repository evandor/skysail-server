package io.skysail.server.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import io.skysail.api.security.AuthenticationService
import org.slf4j.LoggerFactory
import io.skysail.server.app.ApplicationProvider
import io.skysail.server.app.SkysailApplication
import io.skysail.server.app.SkysailApplication._

class RoutesTracker(system: ActorSystem) {

  private val log = LoggerFactory.getLogger(this.getClass)
  
  log info s"instanciating new $this.getClass.getName"

  private var routesBuffer = scala.collection.mutable.ListBuffer[Route]()
  private var routesMap = scala.collection.mutable.Map[String,List[Route]]()
  private val routesCreator = RoutesCreator(system)

  def routes(): List[Route] = routesMap.values.flatMap(_.toList).toList

  def addRoutesFor(appProvider: ApplicationProvider): Unit = {
    log info s" >>> >>> >>> Adding routes from ${appProvider.getClass.getName}"
    val routesFromProvider = appProvider.routes()
    val  valueList: List[Route] = routesFromProvider.map { prt => routesCreator.createRoute(prt, appProvider) }.toList
    val fullList: List[Route] = if (appProvider.nativeRoute() != None) {
      appProvider.nativeRoute().get :: valueList
    } else {
      valueList
    }
    routesMap += appProvider.appModel().id -> fullList
  }

  def removeRoutesFrom(appInfoProvider: ApplicationProvider): Unit = {
    val appsActor = SkysailApplication.getApplicationsActor(system)
    appsActor ! DeleteApplicationActor(appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]])
    log info s" <<< <<< <<< Removing routes from ${appInfoProvider.getClass.getName}"
    routesMap.remove(appInfoProvider.appModel().id)
  }

  def setAuthentication(a: AuthenticationService): Unit = {
    log info s"setting authenticataion to $a"
    routesCreator.authentication = a
  }

}
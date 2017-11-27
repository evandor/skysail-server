package io.skysail.server.app.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.RootApplication

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

case class RootInfo(title: String, description: String, info: String = "you are seeing this as no applications have been deployed yet.")

class RootResource extends EntityResource[RootInfo] {

  override def get(requestEvent: RequestEvent) {
    val appService = application.asInstanceOf[RootApplication].appService
    val apps: Future[List[Application]] = appService.getAllApplications(this.actorContext.system)
    apps.onComplete {
      case Success(s) => {
        val skysailServerBundle = bundleContext.getBundles.filter(_.getSymbolicName == "skysail.server").head
        val desc = "powered by skysail " + skysailServerBundle.getVersion
        val msg = if (s.size == 0)
          RootInfo("skysail server", desc)
        else
          RootInfo("skysail server", desc, s"${s.size} app(s) deployed")
        requestEvent.controllerActor ! msg
      }
    }

  }


}
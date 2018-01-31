package io.skysail.server.app.resources

import io.skysail.api.ProductUtils
import io.skysail.domain.{AsyncResponseEvent, RequestEvent, ResponseEvent}
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.RootApplication
import org.osgi.framework.Bundle

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import io.skysail.domain.resources.AsyncEntityResource
import io.skysail.domain.ResponseEventBase
import scala.util.Failure
import org.slf4j.LoggerFactory

case class RootInfo(
                     title: String,
                     description: String,
                     product: String,
                     info: String = "you are seeing this as no applications have been deployed yet."
                   )

class RootResource extends AsyncEntityResource[RootApplication,RootInfo,String] {
  
  private val log = LoggerFactory.getLogger(this.getClass)

  override def getEntityAsync(requestEvent: RequestEvent):Unit = {
    val product: Option[Bundle] = new ProductUtils().getOptionalProducktBundle(bundleContext)
    val productDef = if (product.isDefined) product.get.getSymbolicName else "no product defined"
    val appService = getApplication().appService
    val apps = appService.getAllApplications(this.actorContext.system)
    apps.onComplete {
      case Success(s) => {
        val skysailServerBundle = bundleContext.getBundles.filter(_.getSymbolicName == "skysail.server").head
        val desc = "powered by skysail-server " + skysailServerBundle.getVersion
        val msg = if (s.size <= 1)
          RootInfo("skysail server", desc, productDef)
        else
          RootInfo("skysail server", desc, productDef, s"${s.size} app(s) deployed: " + s.map(_.name).mkString(", "))
        log info s"sending response event with msg $msg"
        requestEvent.controllerActor ! ResponseEvent(requestEvent, msg)
      }
      case Failure(f) => log warn s"failure: $f"
    }
  }

  def get(requestEvent: RequestEvent): ResponseEventBase = {
    ???
  }

//  override def get(requestEvent: RequestEvent):AsyncResponseEvent = {
//    getEntity(requestEvent)
//    AsyncResponseEvent(requestEvent)
//  }
}
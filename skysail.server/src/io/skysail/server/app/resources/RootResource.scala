package io.skysail.server.app.resources

import io.skysail.api.ProductUtils
import io.skysail.domain.resources.AsyncEntityResource
import io.skysail.domain.{RequestEvent, ResponseEvent, ResponseEventBase}
import io.skysail.server.app.RootApplication
import org.osgi.framework.Bundle
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

case class RootInfo(
                     title: String,
                     description: String,
                     product: String,
                     info: String = "you are seeing this as no applications have been deployed yet."
                   )

class RootResource extends AsyncEntityResource[RootApplication,RootInfo] {
  
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

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {null}

}
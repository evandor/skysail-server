package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import akka.util.Timeout
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.routes._
import io.skysail.domain.{RequestEvent, ResponseEventBase, SkysailResource}

import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._

abstract class AsyncResource[S <: ApplicationApi, T: TypeTag]
  extends SkysailResource[S, T]
    with ActorContextAware {

  implicit val timeout: Timeout = 3.seconds

  def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit

  def getHtmlTemplates(req: RequestEvent):List[String] = {
    val resName = req.cmd.mapping.resourceClass
    req.cmd.mapping match {
      case c: CreationMapping[_, _] => {
        req.cmd.ctx.request.method match {
          case HttpMethods.GET => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Form")
          case HttpMethods.POST => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Post")
          case _ => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Get")
        }
      }
      case c: ListRouteMapping[_, _] => List(
          "html.ListResource_Get",
          s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Get"
          )
      case c: EntityMapping[_, _] => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Entity")
      case c: UpdateMapping[_, _] => {
        req.cmd.ctx.request.method match {
          case HttpMethods.GET => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_UpdateForm")
          case HttpMethods.PUT => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Post")
          case _ => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Get")
        }
      }
      case c: RouteMapping[_, _] => List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Get")
    }

  }

  final def doGet(requestEvent: RequestEvent): Unit = {
    requestEvent.controllerActor ! get(requestEvent)
  }

  def get(requestEvent: RequestEvent): ResponseEventBase

  implicit class TypeDetector[T: TypeTag](related: SkysailResource[_, T]) {
    def getType(): Type = typeOf[T]
  }

}
package io.skysail.domain.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain._
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes._
import org.slf4j.LoggerFactory

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

/**
 * A DefaultResource[S,T] provides a list of route mappings to list, show, create, update and delete
 * entities of type T.
 *
 * It handles associated requests to the endpoints of the mappings.
 *
 * S the backend application serving the resource
 * T the entity associated with the resource, typically an aggregate root
 */
abstract class DefaultResource[S <: ApplicationApi, T: TypeTag, L: TypeTag] extends AsyncResource[S, List[T]] {

  private val log = LoggerFactory.getLogger(this.getClass)
  
  val entityManifest: Manifest[T] = Transformer.toManifest
  val entityClassTag: ClassTag[T] = Transformer.toClassTag

  val listManifest: Manifest[L] = Transformer.toManifest
  val listClassTag: ClassTag[L] = Transformer.toClassTag

  override def handleRequest(cmd: ProcessCommand, controller: ActorRef)(implicit system: ActorSystem): Unit = {
    // tag::methodMatch[]
    cmd.mapping match {
      case c: ListRouteMapping[_, _] => handleListRouteMapping(RequestEvent(cmd, controller))
      case c: EntityMapping[_, _] => handleEntityMapping(RequestEvent(cmd, controller))
      case c: CreationMapping[_, _] => {
        cmd.ctx.request.method match {
          case HttpMethods.GET => handleCreationMappingGet(RequestEvent(cmd, controller))
          case HttpMethods.POST => handleCreationMappingPost(RequestEvent(cmd, controller))
          case _ => log warn s"unknown CreationMapping"
        }
      }
      case c: UpdateMapping[_, _] => {
        cmd.ctx.request.method match {
          case HttpMethods.GET => handleUpdateMappingGet(RequestEvent(cmd, controller))
          case HttpMethods.PUT => handleUpdateMappingPut(RequestEvent(cmd, controller))
          case _ => log warn s"unknown UpdateMapping"
        }
      }
      case _ => log warn s"unknown mapping ${cmd.mapping}"
    }
    // end::methodMatch[]

  }

  final def handleListRouteMapping(re: RequestEvent) = {
    val list: L = getList(re)
    println(listManifest)
    re.controllerActor ! ResponseEvent[L](re, list)(listManifest, listClassTag)
  }

  final def handleEntityMapping(re: RequestEvent) = {
    val entity = getEntity(re).get
    re.controllerActor ! ResponseEvent[T](re, entity)(entityManifest, entityClassTag)
  }

  final def handleCreationMappingGet(re: RequestEvent) = {
    re.controllerActor ! ResponseEvent[T](re, getTemplate(re))(entityManifest, entityClassTag)
  }

  final def handleCreationMappingPost(re: RequestEvent)(implicit system: ActorSystem) = {
    createEntity(re)
    val newRequest = re.cmd.ctx.request.copy(method = HttpMethods.GET) // ???
    re.controllerActor ! RedirectResponseEvent(re, "", getRedirectAfterPost(re))
  }

  def handleUpdateMappingGet(re: RequestEvent): Unit = {
    val optionalEntity = getEntity(re)
    re.controllerActor ! ResponseEvent(re, optionalEntity.get)(entityManifest, entityClassTag)
  }

  def handleUpdateMappingPut(re: RequestEvent)(implicit system: ActorSystem) = {
    updateEntity(re)
    val newRequest = re.cmd.ctx.request.copy(method = HttpMethods.GET) // ???
    re.controllerActor ! RedirectResponseEvent(re, "", getRedirectAfterPut(re))
    
  }

  def getList(requestEvent: RequestEvent): L

  def getEntity(re: RequestEvent): Option[T]

  def getTemplate(re: RequestEvent): T

  def getRedirectAfterPost(re: RequestEvent): Option[String]
  
  def getRedirectAfterPut(re: RequestEvent): Option[String]

  def createEntity(re: RequestEvent)(implicit system: ActorSystem): String
  
  def updateEntity(re: RequestEvent)(implicit system: ActorSystem): Unit
  
  
 // def get(re: RequestEvent): ResponseEventBase = ResponseEvent[T](re, getList(re))

  def getMappings(cls: Class[_ <: DefaultResource[_, _,_]], appModel: ApplicationModel): List[RouteMappingI[_, T]] = {
    val root = appModel.appRoute
    val entityName = typeOf[T].typeSymbol.name.toString().toLowerCase()
    val theClass = cls.asInstanceOf[Class[SkysailResource[_, T]]]
    List(
      ListRouteMapping(s"/${entityName}s", root / PathMatcher(s"${entityName}s") ~ PathEnd, theClass),
      CreationMapping(s"/${entityName}s/", root / PathMatcher(s"${entityName}s") / PathEnd, theClass),
      EntityMapping(s"/${entityName}s/:id", root / PathMatcher(s"${entityName}s") / Segment ~ PathEnd,  theClass),
      UpdateMapping(s"/${entityName}s/:id/", root / PathMatcher(s"${entityName}s") / Segment / PathEnd, theClass)
    )
  }

}
package io.skysail.domain.resources

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain._
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes._
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.universe._
import scala.util.{ Failure, Success }
import org.json4s.JsonAST.JValue
import scala.reflect.ClassTag
import scala.reflect.ManifestFactory

/**
 * A DefaultResource[S,T] provides a list of route mappings to list, show, create, update and delete
 * entities of type T.
 *
 * It handles associated requests to the endpoints of the mappings.
 *
 * @param <S> the backend application serving the resource
 * @param <T> the entity associated with the resource, typically an aggregate root
 */
abstract class DefaultResource[S <: ApplicationApi, T: TypeTag] extends AsyncResource[S, List[T]] {

  private val log = LoggerFactory.getLogger(this.getClass)

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
      case _ => log warn s"unknown mapping"
    }
    // end::methodMatch[]

  }

  final def handleListRouteMapping(re: RequestEvent) = re.controllerActor ! ListResponseEvent[List[T]](re, getList(re))

  final def handleEntityMapping(re: RequestEvent) = {
    val entity = getEntity(re).get
    
    val m = toManifest
    
    val json: JValue = Transformer.beanToJson(entity)(m)
    println("hier: " + json)
    re.controllerActor ! ResponseEvent[T](re, entity)
  }

  final def handleCreationMappingGet(re: RequestEvent) = re.controllerActor ! ResponseEvent[T](re, getTemplate(re))

  final def handleCreationMappingPost(re: RequestEvent)(implicit system: ActorSystem) = {
    createEntity(re)
    val newRequest = re.cmd.ctx.request.copy(method = HttpMethods.GET) // ???
    re.controllerActor ! RedirectResponseEvent(re, "", getRedirectAfterPost(re))
  }

  def handleUpdateMappingGet(re: RequestEvent): Unit = {
    val optionalEntity = getEntity(re)
    re.controllerActor ! ResponseEvent(re, optionalEntity.get)
  }

  def handleUpdateMappingPut(re: RequestEvent)(implicit system: ActorSystem) = {
    updateEntity(re)
    val newRequest = re.cmd.ctx.request.copy(method = HttpMethods.GET) // ???
    re.controllerActor ! RedirectResponseEvent(re, "", getRedirectAfterPut(re))
    
  }
  
  

  def getList(requestEvent: RequestEvent): List[T]

  def getEntity(re: RequestEvent): Option[T]

  def getTemplate(re: RequestEvent): T

  def getRedirectAfterPost(re: RequestEvent): Option[String]
  
  def getRedirectAfterPut(re: RequestEvent): Option[String]

  def createEntity(re: RequestEvent)(implicit system: ActorSystem): String
  
  def updateEntity(re: RequestEvent)(implicit system: ActorSystem): Unit
  
  
  def get(re: RequestEvent): ResponseEventBase = ListResponseEvent[List[T]](re, getList(re))

  def getMappings(cls: Class[_ <: DefaultResource[_, _]], appModel: ApplicationModel): List[RouteMappingI[_, T]] = {
    val root = appModel.appRoute
    val entityName = typeOf[T].typeSymbol.name.toString().toLowerCase()
    List(
      ListRouteMapping(s"/${entityName}s", root / PathMatcher(s"${entityName}s") ~ PathEnd, cls.asInstanceOf[Class[SkysailResource[_, T]]]),
      CreationMapping(s"/${entityName}s/", root / PathMatcher(s"${entityName}s") / PathEnd, cls.asInstanceOf[Class[SkysailResource[_, T]]]),
      EntityMapping(s"/${entityName}s/:id", root / PathMatcher(s"${entityName}s") / Segment ~ PathEnd, cls.asInstanceOf[Class[SkysailResource[_, T]]]),
      UpdateMapping(s"/${entityName}s/:id/", root / PathMatcher(s"${entityName}s") / Segment / PathEnd, cls.asInstanceOf[Class[SkysailResource[_, T]]]))
  }

  def reply(requestEvent: RequestEvent, answer: Future[List[_]]): Unit = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent(requestEvent, answer)
      case Failure(f) => println(s"failure $f")
    }
  }

  def reply[U](requestEvent: RequestEvent, answer: Future[List[U]], c: List[U] => List[T]): Unit = {
    answer.onComplete {
      case Success(s) => requestEvent.controllerActor ! ListResponseEvent[List[T]](requestEvent, c.apply(s))
      case Failure(f) => println(s"failure $f")
    }
  }
  
  private def toManifest[T:TypeTag]: Manifest[T] = {
    val t = typeTag[T]
    val mirror = t.mirror
    def toManifestRec(t: Type): Manifest[_] = {
      val clazz = ClassTag[T](mirror.runtimeClass(t)).runtimeClass
      if (t.typeArgs.length == 1) {
        val arg = toManifestRec(t.typeArgs.head)
        ManifestFactory.classType(clazz, arg)
      } else if (t.typeArgs.length > 1) {
        val args = t.typeArgs.map(x => toManifestRec(x))
        ManifestFactory.classType(clazz, args.head, args.tail: _*)
      } else {
        ManifestFactory.classType(clazz)
      }
    }
    toManifestRec(t.tpe).asInstanceOf[Manifest[T]]
  }

}
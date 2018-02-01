package io.skysail.server.actors

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import akka.actor.{Actor, ActorRef}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{ContentNegotiator, MediaTypeNegotiator}
import akka.util.Timeout
import com.fasterxml.jackson.annotation.JsonProperty
import io.skysail.api.ui.Link
import io.skysail.domain.{Transformer, _}
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.AsyncResource
import io.skysail.server.RepresentationModel
import io.skysail.server.actors.ApplicationActor._
import org.json4s.JsonAST.{JArray, JString, JValue}
import org.json4s.jackson.JsonMethods.{compact, render}
import org.json4s.jackson.Serialization
import org.json4s.{CustomSerializer, DefaultFormats, FieldSerializer, JObject, jackson}
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory
import play.twirl.api.HtmlFormat

import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag

case class Person(name: String, lastLogin: ZonedDateTime) {
  @JsonProperty
  val test = "Test"
  
  val test2:List[Link] = List(Link("hi", "there"))

  def test3() = "Test3"
}

case object ZDTSerializer extends CustomSerializer[ZonedDateTime](format => ( {
  case JString(s) => ZonedDateTime.parse(s)
}, {
  case zdt: ZonedDateTime => JString(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")))
}))

//case object LinkSerializer extends FieldSerializer[Person] 

object ControllerActor {

  case class GetRequest()

  case class PostRequest()

  case class PutRequest()

  case class DeleteRequest()

  case class MyResponseEntity(entity: ResponseEntity)

}

class ControllerActor() extends Actor {

  implicit val askTimeout: Timeout = 3.seconds

  private val log = LoggerFactory.getLogger(this.getClass)

  var applicationActor: ActorRef = _
  var applicationModel: ApplicationModel =
    _

  import context._

  def receive: Receive = in

  def in: Receive = {
    case SkysailContext(cmd: ProcessCommand, model: ApplicationModel, resource: AsyncResource[_, _], bc: BundleContext) =>
      applicationActor = sender
      applicationModel = model

      log debug s"  [IN] >>> COMMAND:  $cmd"
      log debug s"  [IN] >>> MODEL:    $model"
      //log info s"  [IN] >>> RESOURCE: $resource"
      log debug s"  [IN] >>> ENTITY:   ${cmd.entity}"

      resource.setActorContext(context)
      resource.setApplicationModel(model)
      resource.setApplication(cmd.application)
      resource.setBundleContext(bc)

      val cmdWithResource = cmd.copy(resource = Some(resource))

      resource.handleRequest(cmdWithResource, self)

      become(out)
    case msg: Any => log info s"<<< IN <<<: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out[T:Manifest, ClassTag]: Receive = {

    case response: ListResponseEvent[T] =>

      log debug s"  [OUT] >>> $response"

      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      val cn = ContentNegotiator(response.req.cmd.ctx.request.headers)
      val amr = cn.mtn.acceptedMediaRanges

      implicit val formats: DefaultFormats.type = DefaultFormats
      implicit val serialization: Serialization.type = jackson.Serialization

      val ast:JValue = if (response.entityManifest != null) {
        val em: Manifest[T] = response.entityManifest
        Transformer.listToJson(response.entity)(em)
      } else {
        Transformer.beanToJson(response.entity)
      }

      ast match {
        case a: JArray =>
          val b: String = compact(render(a))
          //println("YYY " + b)
          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback[T](response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }

        case _ => log warn "unknown match"
      }

    case response: ResponseEvent[T] =>

      log debug s"  [OUT] >>> $response"
      
      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      val ast:JValue = if (response.entityManifest != null) {
        Transformer.beanToJson(response.entity)(response.entityManifest)
      } else {
        Transformer.beanToJson(response.entity)
      }

      ast match {
        case a: JObject =>
          val b: String = compact(render(a))

          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback[T](response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }
        case a: JString =>
          val b: String = compact(render(a))
          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback[T](response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }
        case _: Any =>
          log warn s"could not match response.entity ${response.entity}"
          applicationActor ! response
      }
    case response: HtmlResponseEvent =>
      log debug s"  [OUT] >>> $response"
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, response.entity)
      applicationActor ! response.copy(entity = response.entity, httpResponse = response.httpResponse.copy(entity = answer))
    case response: RedirectResponseEvent =>
      log debug s"  [OUT] >>> $response"
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, response.entity)
      val uri = response.redirectTo.getOrElse("/").toString
      applicationActor ! response.copy(
        //req = response.req.copy(
        entity = response.entity,
        httpResponse = response.httpResponse.copy(
          status = StatusCodes.SeeOther, // not 307, see https://en.wikipedia.org/wiki/List_of_HTTP_status_codes#3xx_Redirection, https://stackoverflow.com/questions/2068418/whats-the-difference-between-a-302-and-a-307-redirect
          headers = headers.Location(akka.http.scaladsl.model.Uri(uri)) :: Nil,
          entity = answer))
    case response: AsyncResponseEvent =>
      log debug s"  [OUT] >>> $response"
      log info s"async response event, no action needed"

    case msg: Any =>
      log warn "============================================================================"
      log warn s"  [OUT] >>> $msg' in ${this.getClass.getName}"
      log warn "============================================================================"
  }

  private def handleHtmlWithFallback[T:ClassTag](response: ResponseEventBase, json: String)(implicit ct: ClassTag[T]): Unit = {
    val templateNames = getHtmlTemplates(response.req, response.getResource)
    val loader = response.req.cmd.mapping.resourceClass.getClassLoader
    val answer: Option[ResponseEntity] = templateNames
      .map(name => tryLoading[T](name, response, loader)(ct))
      .find(_.isDefined)
      .flatMap(identity)

    if (answer.isDefined) {
      response match {
        case ListResponseEvent(req, _,_, _) => applicationActor ! ListResponseEvent(req, response.entity.asInstanceOf[List[_]], null, response.httpResponse.copy(entity = answer.get))
        case ResponseEvent(req, _, _, _) => applicationActor ! ResponseEvent(req, response.entity, null, response.httpResponse.copy(entity = answer.get))
        case _ => log warn "unmatched response"
      }
    } else {
      log debug s"        rendering fallback to json, could not load '$templateNames'"
      handleJson(response, json)
    }
  }

  private def handleJson(response: ResponseEventBase, json: String): Unit = {
    val e = HttpEntity(ContentType(MediaTypes.`application/json`), json)
    val res = response.httpResponse.copy(entity = e)
    response match {
      case ListResponseEvent(req, _, _,  _) => applicationActor ! ListResponseEvent(req, response.entity.asInstanceOf[List[_]],  null,res)
      case ResponseEvent(req, _, _,_) => applicationActor ! ResponseEvent(req, response.entity, null, res)
      case _ => log warn "unmatched response"
    }
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    //log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

  private def getHtmlTemplates(req: RequestEvent, resource: Option[AsyncResource[_, _]]) = {
    val resName = req.cmd.mapping.resourceClass
    if (resource.isDefined)
      resource.get.getHtmlTemplates(req)
    else
      List(s"${resName.getPackage.getName}.html.${resName.getSimpleName}_Get")
  }

  private def tryLoading[T](templateName: String, response: ResponseEventBase, loader: ClassLoader)(implicit ct: ClassTag[T]): Option[ResponseEntity] = {
    try {
      val resourceHtmlClass = loader.loadClass(templateName)
      val applyMethod = resourceHtmlClass.getMethod("apply", classOf[RepresentationModel], classOf[ResponseEventBase], ct.runtimeClass)
      val rep = new RepresentationModel(response, applicationModel)
      val r2 = applyMethod.invoke(resourceHtmlClass, rep, response).asInstanceOf[HtmlFormat.Appendable]
      Some(HttpEntity(ContentTypes.`text/html(UTF-8)`, r2.body))
    } catch {
      case ex: Exception => log debug s"problem: ${ex.getMessage}"; /*ex.printStackTrace();*/ None
    }
  }

}

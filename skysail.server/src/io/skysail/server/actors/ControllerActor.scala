package io.skysail.server.actors

import akka.actor.{ Actor, ActorRef }
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{ ContentNegotiator, MediaTypeNegotiator }
import akka.util.Timeout
import io.skysail.domain._
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.AsyncResource
import io.skysail.server.RepresentationModel
import io.skysail.server.actors.ApplicationActor._
import org.json4s.JsonAST.{ JArray, JString }
import org.json4s.jackson.JsonMethods.{ compact, render }
import org.json4s.jackson.Serialization
import org.json4s.{ DefaultFormats, Extraction, JObject, jackson }
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory
import play.twirl.api.HtmlFormat
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write

import scala.concurrent.duration.DurationInt
import org.json4s.JsonAST.JValue
import com.fasterxml.jackson.annotation.JsonProperty
import org.json4s.CustomSerializer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.json4s.FieldSerializer
import io.skysail.api.ui.Link
import io.skysail.api.Transformer
import scala.reflect.runtime.universe._

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

      log info s"  [IN] >>> COMMAND:  $cmd"
      log info s"  [IN] >>> MODEL:    $model"
      //log info s"  [IN] >>> RESOURCE: $resource"
      log info s"  [IN] >>> ENTITY:   ${cmd.entity}"

      resource.setActorContext(context)
      resource.setApplicationModel(model)
      resource.setApplication(cmd.application)
      resource.setBundleContext(bc)

      val cmdWithResource = cmd.copy(resource = Some(resource))

      resource.handleRequest(cmdWithResource, self)

      become(out)
    case msg: Any => log info s"<<< IN <<<: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out[T:Manifest]: Receive = {

    case response: ListResponseEvent[T] =>

      log info s"  [OUT] >>> $response"

      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      val cn = ContentNegotiator(response.req.cmd.ctx.request.headers)
      val amr = cn.mtn.acceptedMediaRanges

      implicit val formats: DefaultFormats.type = DefaultFormats
      implicit val serialization: Serialization.type = jackson.Serialization

      //val m = Marshal(response.entity.asInstanceOf[List[_]]).to[RequestEntity]
      val e: T = response.entity
      println(e)

      //val written = org.json4s.jackson.Serialization.write[T](e)

      val ast: JValue = Extraction.decompose(e)
      ast match {
        case a: JArray =>
          val b: String = compact(render(a))
          println("YYY " + b)
          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback(response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }

        case _ => log warn "unknown match"
      }

    case response: ResponseEvent[T] =>

      log info s"  [OUT] >>> $response"
      
      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      implicit val formats = DefaultFormats + ZDTSerializer + FieldSerializer[Person] ()
      implicit val serialization: Serialization.type = jackson.Serialization

      val e: T = response.entity
      println("class: "+ response.entity.getClass.getName)
      println(e)

      val john = Person("john", ZonedDateTime.now())
      //val written = write(john)
      println("+++"+Transformer.beanToJson(john))
      
      
      val ast:JValue = Transformer.beanToJson(e)

      println(ast)
      //val written = org.json4s.jackson.Serialization.write(e)

      //val e1 = Extraction.decompose(response.entity)

      ast match {
        case a: JObject =>
          val b: String = compact(render(a))

          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback(response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }
        case a: JString =>
          val b: String = compact(render(a))
          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback(response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }
        case _: Any =>
          log warn s"could not match response.entity ${response.entity}"
          applicationActor ! response
      }
    case response: HtmlResponseEvent =>
      log info s"  [OUT] >>> $response"
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, response.entity)
      applicationActor ! response.copy(entity = response.entity, httpResponse = response.httpResponse.copy(entity = answer))
    case response: RedirectResponseEvent =>
      log info s"  [OUT] >>> $response"
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
      log info s"  [OUT] >>> $response"
      log info s"async response event, no action needed"

    case msg: Any =>
      log warn "============================================================================"
      log info s"  [OUT] >>> $msg' in ${this.getClass.getName}"
      log warn "============================================================================"
  }

  private def handleHtmlWithFallback(response: ResponseEventBase, json: String): Unit = {
    val templateNames = getHtmlTemplates(response.req, response.getResource)
    val loader = response.req.cmd.mapping.resourceClass.getClassLoader
    val answer: Option[ResponseEntity] = templateNames
      .map(name => tryLoading(name, response, loader))
      .find(_.isDefined)
      .flatMap(identity)

    if (answer.isDefined) {
      response match {
        case ListResponseEvent(req, _, _) => applicationActor ! ListResponseEvent(req, response.entity, response.httpResponse.copy(entity = answer.get))
        case ResponseEvent(req, _, _) => applicationActor ! ResponseEvent(req, response.entity, response.httpResponse.copy(entity = answer.get))
        case _ => log warn "unmatched response"
      }
    } else {
      log info s"        rendering fallback to json, could not load '$templateNames'"
      handleJson(response, json)
    }
  }

  private def handleJson(response: ResponseEventBase, json: String): Unit = {
    val e = HttpEntity(ContentType(MediaTypes.`application/json`), json)
    val res = response.httpResponse.copy(entity = e)
    response match {
      case ListResponseEvent(req, _, _) => applicationActor ! ListResponseEvent(req, response.entity, res)
      case ResponseEvent(req, _, _) => applicationActor ! ResponseEvent(req, response.entity, res)
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

  private def tryLoading(templateName: String, response: ResponseEventBase, loader: ClassLoader): Option[ResponseEntity] = {
    try {
      val resourceHtmlClass = loader.loadClass(templateName)
      val applyMethod = resourceHtmlClass.getMethod("apply", classOf[RepresentationModel], classOf[ResponseEventBase])
      val rep = new RepresentationModel(response, applicationModel)
      val r2 = applyMethod.invoke(resourceHtmlClass, rep, response).asInstanceOf[HtmlFormat.Appendable]
      Some(HttpEntity(ContentTypes.`text/html(UTF-8)`, r2.body))
    } catch {
      case ex: Exception => log info s"problem: ${ex.getMessage}"; ex.printStackTrace(); None
    }
  }

}

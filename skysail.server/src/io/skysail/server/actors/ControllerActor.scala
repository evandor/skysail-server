package io.skysail.server.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.event.LoggingReceive
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.MediaTypeNegotiator
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import io.skysail.domain._
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.resources.AsyncResource
import io.skysail.server.RepresentationModel
import io.skysail.server.actors.ApplicationActor._
import org.json4s.JsonAST.JString
import org.json4s.jackson.JsonMethods.{compact, render}
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Extraction, JObject, jackson}
import org.osgi.framework.BundleContext
import play.twirl.api.HtmlFormat

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object ControllerActor {

  case class GetRequest()

  case class PostRequest()

  case class PutRequest()

  case class DeleteRequest()

  case class MyResponseEntity(val entity: ResponseEntity)

}

class ControllerActor[T]() extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  var applicationActor: ActorRef = null
  var applicationModel: ApplicationModel = null

  import context._

  def receive = in

  def in: Receive = LoggingReceive {
    case SkysailContext(cmd: ProcessCommand, model: ApplicationModel, resource: AsyncResource[T], bc: BundleContext) => {
      applicationActor = sender
      applicationModel = model
      resource.setActorContext(context)
      resource.setApplicationModel(model)
      resource.setApplication(cmd.application)
      resource.setBundleContext(bc)
      cmd.ctx.request.method match {
        case HttpMethods.GET => resource.get(RequestEvent(cmd, self))
        case HttpMethods.POST => resource.asInstanceOf[PostSupport].post(RequestEvent(cmd, self))
        case e: Any => resource.get(RequestEvent(cmd, self))
      }
      become(out)
    }
    case msg: Any => log info s"<<< IN <<<: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out: Receive = LoggingReceive {
    case response: ListResponseEvent[T] =>
      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization

      val m = Marshal(response.entity.asInstanceOf[List[_]]).to[RequestEntity]

      if (negotiator.isAccepted(MediaTypes.`text/html`)) {
        handleHtmlWithFallback(response, m)
      } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
        handleJson(m, response)
      }
    case response: ResponseEvent[T] => {
      val negotiator = new MediaTypeNegotiator(response.req.cmd.ctx.request.headers)
      val acceptedMediaRanges = negotiator.acceptedMediaRanges

      implicit val formats: DefaultFormats.type = DefaultFormats
      implicit val serialization: Serialization.type = jackson.Serialization

      val e1 = Extraction.decompose(response.entity)

      e1 match {
        case a: JObject => {
          val written: String = write(a)
          val b: String = compact(render(a))

          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
            handleHtmlWithFallback(response, b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
            handleJson(response, b)
          }
        }
        case a: JString => {
          //val e = e1.asInstanceOf[JObject]
          val written: String = write(a)
          val b: String = compact(render(a))

          if (negotiator.isAccepted(MediaTypes.`text/html`)) {
           handleHtmlWithFallback(response,b)
          } else if (negotiator.isAccepted(MediaTypes.`application/json`)) {
           handleJson(response,b)
          }
        }
        case _: Any =>  applicationActor ! response
      }
    }
    case response: HtmlResponseEvent =>
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, response.entity)
      applicationActor ! response.copy(entity = response.entity, httpResponse = response.httpResponse.copy(entity = answer))
    case response: RedirectResponseEvent =>
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, response.entity)
      val uri = response.redirectTo.getOrElse("/").toString
      applicationActor ! response.copy(
        entity = response.entity, httpResponse = response.httpResponse.copy(
          status = StatusCodes.TemporaryRedirect,
          headers = headers.Location(akka.http.scaladsl.model.Uri(uri)) :: Nil,
          entity = answer))
    case msg: List[T] => {
      log warning s">>> OUT(${this.hashCode()}) @deprecated >>>: List[T]"
      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization
      val m = Marshal(msg).to[RequestEntity]
      m.onSuccess {
        case value =>
          val reqEvent = RequestEvent(null, null)
          val resEvent = ListResponseEvent(reqEvent, null)
          log info s">>> OUT(${this.hashCode()} >>>: sending back to ${applicationActor}"
          applicationActor ! resEvent.copy(entity = msg, httpResponse = resEvent.httpResponse.copy(entity = value))
      }
    }
    case msg: ControllerActor.MyResponseEntity => {
      log warning s">>> OUT(${this.hashCode()}) @deprecated >>>: ControllerActor.MyResponseEntity"
      val reqEvent = RequestEvent(null, null)
      val resEvent = ListResponseEvent(reqEvent, null)
      applicationActor ! resEvent.copy(httpResponse = resEvent.httpResponse.copy(entity = msg.entity))
    }
    case msg: T => {
      log warning s">>> OUT(${this.hashCode()}) @deprecated >>>: T [msg:${msg.getClass.getName}]"
      val reqEvent = RequestEvent(null, null)
      val resEvent = ListResponseEvent(reqEvent, null)
      implicit val formats = DefaultFormats
      val e = Extraction.decompose(msg).asInstanceOf[JObject]
      val written = write(e)
      val r = HttpEntity(ContentTypes.`application/json`, written)
      applicationActor ! resEvent.copy(entity = msg, httpResponse = resEvent.httpResponse.copy(entity = r))
    }
    case msg: Any => log info s">>> OUT >>>: received unknown message '$msg' in ${this.getClass.getName}"
  }

  private def handleHtmlWithFallback(response: ListResponseEvent[T], m: Future[MessageEntity]) = {
    try {
      val loader = response.req.cmd.resourceClass.getClassLoader
      val resourceHtmlClass = loader.loadClass(getHtmlTemplate(response.req))
      val applyMethod = resourceHtmlClass.getMethod("apply", classOf[RepresentationModel])

      m.onSuccess {
        case value =>
          val rep = new RepresentationModel(response, applicationModel)
          val r2 = applyMethod.invoke(resourceHtmlClass, rep).asInstanceOf[HtmlFormat.Appendable]
          val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, r2.body)
          applicationActor ! response.copy(entity = response.entity, httpResponse = response.httpResponse.copy(entity = answer))
      }

    } catch {
      case e: Exception => log info s"rendering fallback to json, could not load '${getHtmlTemplate(response.req)}', reason: $e"; handleJson(m, response)
    }
  }

  private def handleHtmlWithFallback(response: ResponseEvent[T], e: String): Unit = {
    val resourceClassAsString = getHtmlTemplate(response.req)
    try {
      val loader = response.req.cmd.resourceClass.getClassLoader
      val resourceHtmlClass = loader.loadClass(resourceClassAsString)
      val applyMethod = resourceHtmlClass.getMethod("apply", classOf[RepresentationModel])

      val rep = new RepresentationModel(response, applicationModel)
      val r2 = applyMethod.invoke(resourceHtmlClass, rep).asInstanceOf[HtmlFormat.Appendable]
      val answer = HttpEntity(ContentTypes.`text/html(UTF-8)`, r2.body)
      applicationActor ! response.copy(entity = response.entity, httpResponse = response.httpResponse.copy(entity = answer))

    } catch {
      case ex: Exception =>
        log info s"rendering fallback to json, could not load '$resourceClassAsString', reason: $ex"
        handleJson(response, e)
    }

  }


  private def handleJson(m: Future[MessageEntity], response: ListResponseEvent[T]) = {
    m.onSuccess {
      case value =>
        applicationActor ! response.copy(entity = response.entity, httpResponse = response.httpResponse.copy(entity = value))
    }
  }

  private def handleJson(response: ResponseEvent[T], e: String) = {
    applicationActor ! response.copy(entity = response.entity,
      httpResponse = response.httpResponse.copy(entity = e))
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

  private def getHtmlTemplate(req: RequestEvent) = {
    s"${req.cmd.resourceClass.getPackage.getName}.html.${req.cmd.resourceClass.getSimpleName}_Get"
  }

}

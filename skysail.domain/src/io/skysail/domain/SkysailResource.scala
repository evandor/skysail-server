package io.skysail.domain

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onComplete}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.model.ApplicationModel
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

object SkysailResource {

  implicit class TypeDetector[T: TypeTag](related: SkysailResource[_,T]) {
    def getType(): Type = typeOf[T]
  }

}

/**
  * The abstract base class for skysail resources, which define how to handle requests to
  * predefined endpoints.
  *
  * Those endpoints are specified using @link{RouteMapping}s, which get "collected" during
  * bundle activation.
  *
  * @tparam T
  */
abstract class SkysailResource[S <: ApplicationApi, T: TypeTag] {

  private val log = LoggerFactory.getLogger(this.getClass)

  implicit val askTimeout: Timeout = 3.seconds

  var applicationModel: ApplicationModel = null
  var bundleContext: BundleContext = null

  private var application: ApplicationApi = null

  def setApplicationModel(model: ApplicationModel) = this.applicationModel = model

  def setBundleContext(bc: BundleContext) = this.bundleContext = bc

  def setApplication(app: ApplicationApi) = this.application = app

  def getApplication(): S = {
    application.asInstanceOf[S]
  }

  def createRoute(applicationActor: ActorSelection, processCommand: io.skysail.domain.messages.ProcessCommand)(implicit system: ActorSystem): Route = {
    val t = (applicationActor ? processCommand).mapTo[ResponseEventBase]
    onComplete(t) {
      case Success(result) =>
        val response = result.httpResponse
        complete(response)
      case Failure(failure) => log error s"Failure>>> ${failure}";  complete(StatusCodes.BadRequest, failure)
    }
  }
}

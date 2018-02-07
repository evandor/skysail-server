package io.skysail.server.actors

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Actor, ActorInitializationException, ActorKilledException, ActorLogging, DeathPactException, OneForOneStrategy, Props}
import akka.event.LoggingReceive
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.{ResponseEventBase, SkysailResource}
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.routes.ConcreteRouteMapping
import io.skysail.server.actors.ApplicationActor._
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.BundleContext

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}
import scala.reflect.runtime.{universe => ru}
import ru._

object ApplicationActor {

  case class GetAppModel()

  case class GetApplication()

  case class SkysailContext(cmd: ProcessCommand, appModel: ApplicationModel, resource: SkysailResource[_ <: ApplicationApi,_], bundleContext: BundleContext)

  case class GetMenu()

  //case class ProcessCommand(ctx: RequestContext, cls: Class[_ <: Resource[_]], urlParameter: List[String], unmatchedPath: Uri.Path, entity: Any = null)

}

/**
  * An ApplicationActor handles various messages related to a single skysail application.
  *
  * Each (running?) skysail application has an associated ApplicationActor which deals with various messages, the
  * most generic one of which is to handle a ProcessCommand.
  *
  * A ProcessCommand demands a specific resource inside the current application to handle a users HTTP request.
  * This is done using a new ControllerActor, which is sent a SkysailContext message containing a newly created
  * instance of the specific resource mentioned above.
  *
  */
class ApplicationActor(appModel: ApplicationModel, application: BackendApplication, bundleContext: BundleContext) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 3.seconds

  val cnt = new AtomicInteger(0)

  import context._

  def receive: Receive = LoggingReceive {
    // tag::resourceInstance[]
    case cmd: ProcessCommand => {

      log debug s"COMMAND: $cmd"
      //log info s"[IN] >>> ENTITY:     ${cmd.entity}"

      val routesCreator = sender()


      val resourceInstance = cmd.mapping match {
        case crm: ConcreteRouteMapping[_,_,_] => crm.resource
        case mapping: Any => cmd.mapping.resourceInstance.asInstanceOf[SkysailResource[_ <: ApplicationApi, _]]
      }

      //val resourceInstance = cmd.mapping.resourceClass.newInstance().asInstanceOf[SkysailResource[_ <: ApplicationApi, _]]
      val controllerActor = createController(cmd.mapping.resourceClass)

      val skysailContext = SkysailContext(cmd, appModel, resourceInstance, bundleContext)
      val res = (controllerActor ? skysailContext).mapTo[ResponseEventBase]
      res onComplete {
        case Success(responseEvent) => routesCreator ! responseEvent
        case Failure(failure) => log error s"Failure >>> $failure"
      }
    }
    // end::resourceInstance[]
    case _: GetApplication => sender ! application
    case _: GetAppModel => sender ! appModel
    case _: GetMenu => getMenuIfExistent()
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

  override val supervisorStrategy =
    OneForOneStrategy() {
      case _: ClassNotFoundException       => Stop
      case _: ActorInitializationException => Stop
      case _: ActorKilledException         => Stop
      case _: DeathPactException           => Stop
      case _: Exception                    => Restart
    }

  def getMenuIfExistent() = {
    if (application.isInstanceOf[ApplicationProvider]) {
      val appProvider = application.asInstanceOf[ApplicationProvider]
      val optionalMenu = None//appProvider.menu()
      sender ! optionalMenu
    } else {
      sender ! None
    }
  }

  private def createController(res: Class[_ <: SkysailResource[_, _]]) = {
    //typeOf[res]
    context.actorOf(Props.apply(classOf[ControllerActor]), "controllerActor$" + cnt.getAndIncrement)
  }

  private def createInstance(tpe:Type): Any = {
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val clsSym = tpe.typeSymbol.asClass
    val clsMirror = mirror.reflectClass(clsSym)
    val ctorSym = tpe.decl(ru.termNames.CONSTRUCTOR).asMethod
    val ctorMirror = clsMirror.reflectConstructor(ctorSym)
    val instance = ctorMirror()
    return instance
}

}
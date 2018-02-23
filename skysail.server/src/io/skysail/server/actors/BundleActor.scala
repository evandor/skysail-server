package io.skysail.server.actors

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.server.RequestContext
import io.skysail.server.actors.BundleActor._
import org.osgi.framework.Bundle
import org.osgi.framework.wiring.BundleWiring

object BundleActor {
  case class GetResource(val path: String)
  case class GetAppModel()
  case class GetClassloader()
}

class BundleActor(bundle: Bundle) extends Actor with ActorLogging {
  
  val cnt = new AtomicInteger(0)

  def receive: Receive = {
    case gr: GetResource => getResource(gr)
    case (ctx:RequestContext,cls : Class[_])  => {
      val sendBackTo = sender
      log debug s"in AppActor... got message ($ctx, $cls)"
      val nextActor = context.actorOf(Props.apply(cls)) // ResourceActor, e.g. AppsResource
      nextActor ! ctx
    }
    case gc: GetClassloader => {
       sender ! bundle.adapt(classOf[BundleWiring]).getClassLoader
    }
    //case _: ApplicationActor.GetAppModel => sender ! appModel.puml
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

   def getResource(gr: GetResource): Unit = {
    log info s"getting asset '${gr.path}' from ${bundle.getSymbolicName}"
    sender ! bundle.getResource(gr.path)
  }

  
}
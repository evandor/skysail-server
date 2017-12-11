package io.skysail.domain.resources

import akka.actor.{Actor, ActorLogging}

class TestControllerActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case msg: Any => log info s"<<< IN <<<: received unknown message '$msg' in ${this.getClass.getName}"
  }
}


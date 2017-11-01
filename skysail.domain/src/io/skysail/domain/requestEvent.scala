package io.skysail.domain

import akka.actor.ActorRef

case class RequestEvent(cmd: io.skysail.domain.messages.ProcessCommand, controllerActor: ActorRef)

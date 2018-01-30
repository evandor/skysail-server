package io.skysail.server.demo.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources._
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Account
import org.slf4j.LoggerFactory

class AccountsResource extends DefaultResource[DemoApplication, Account] {
  
  private val log = LoggerFactory.getLogger(this.getClass)

  override def getList(re: RequestEvent) = getApplication().accountsRepo.find()

  override def getEntity(re: RequestEvent) = getApplication().accountsRepo.find(re.cmd.urlParameter.head)

  override def getTemplate(re: RequestEvent) = Account(None, "", 0)

  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/accounts")

  override def getRedirectAfterPut(re: RequestEvent): Option[String] = Some("/demo/v1/accounts")

  override def createEntity(requestEvent: RequestEvent)(implicit system: ActorSystem):String = {
    val b = getApplication().accountsRepo.save(requestEvent.cmd.entity)
    getApplication().eventService.send("Account created")
    b
  }

  override def updateEntity(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalAccount = getApplication().accountsRepo.find(requestEvent.cmd.urlParameter.head)
    val updatedAccount = requestEvent.cmd.entity.asInstanceOf[Account]
    val AccountToSave = updatedAccount.copy(id = optionalAccount.get.id)
    getApplication().accountsRepo.save(AccountToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      var initial = 0
      try { initial = Integer.parseInt(map.getOrElse("initial", "0")) } catch { case _: Throwable => log debug s"could not parse ${map.get("initial")}"}
      val entity = Account(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), initial)
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

}


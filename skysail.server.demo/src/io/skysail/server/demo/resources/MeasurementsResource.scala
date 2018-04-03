package io.skysail.server.demo.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.EntityResource
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.MeasurementList

class MeasurementsResource extends EntityResource[DemoApplication, MeasurementList] {
  //DefaultResource[DemoApplication, Measurement, MeasurementList] {

  override def getEntity(re: RequestEvent): Option[MeasurementList] = {
//    val id = re.firstParam()
//    val r: Option[Monitor2] = monitorRepo.find(id)
//    r match {
//      case None => MeasurementList(List())
//      case Some(s) => MeasurementList(s.measurements)
//    }
    getApplication().monitor2Repo.find(re.firstParam()).map(m => MeasurementList(m.measurements))
  }

  //  override def repo: RepositoryApi[Measurement] = null
  //
  //  def monitorRepo: RepositoryApi[Monitor2] = getApplication().monitor2Repo
  //
  //  override def getTemplate(re: RequestEvent) = Measurement(None, 0L)
  //
  //  override def getRedirectAfterPost(re: RequestEvent): Option[String] = Some("/demo/v1/monitor2s")
  //
  //  override def updateEntity(re: RequestEvent)(implicit system: ActorSystem): Unit = {
  //    val updatedEntity = re.cmd.entity.asInstanceOf[Monitor2]
  //    val entityToSave = updatedEntity.copy(id = Some(re.firstParam()))
  //    repo.save(entityToSave)
  //  }
  //
  //  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
  //    formFieldMap { map =>
  //      val entity = Monitor2(
  //        None,
  //        map.getOrElse("name", "Unknown"),
  //        map.getOrElse("url", ""),
  //        List()
  //      )
  //      super.createRoute(applicationActor, processCommand.copy(entity = entity))
  //    }
  //  }
}


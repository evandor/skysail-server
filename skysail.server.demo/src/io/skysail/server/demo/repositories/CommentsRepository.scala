//package io.skysail.server.demo.repositories
//
//import io.skysail.api.persistence.DbService
//import io.skysail.domain.model.ApplicationModel
//import io.skysail.server.demo.domain.Comment1
//import io.skysail.db.orientdb.repositories.ResourceRepository
//
//class Comments1Repository(dbService: DbService, appModel: ApplicationModel)
//  extends ResourceRepository[Comment1](classOf[Comment1], dbService, appModel) {
//
//  override def getEntityTemplate() = Comment1(None, "")
//}
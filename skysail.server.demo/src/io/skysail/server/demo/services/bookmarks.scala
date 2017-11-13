package io.skysail.server.demo.services

import io.skysail.server.demo.domain.Bookmark

import scala.concurrent.Future

class BookmarksService {

  def createApplication(app: Bookmark): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Bookmark]] = ???


}

package io.skysail.server.demo.services

import io.skysail.server.demo.domain.Bookmark

import scala.concurrent.Future

class BookmarksService {

  def createApplication(app: Bookmark): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Bookmark]] = ???

  def getApplications(): Seq[Bookmark] = List(
    Bookmark(None,"skysail","http://www.skysail.io"),
    Bookmark(null,"pline","http://www.pline.one")
  )

}

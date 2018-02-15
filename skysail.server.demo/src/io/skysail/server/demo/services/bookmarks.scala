package io.skysail.server.demo.services

import io.skysail.server.adapter.JSoupAdapter
import io.skysail.server.demo.domain.Bookmark
import java.security.MessageDigest
import org.jsoup.nodes.Document

import scala.util.{Failure, Success, Try}

object BookmarksService {


  def addMetadata(bookmark: Bookmark) = {
    var bm = bookmark
    //.copy()
    val metadata: Try[Document] = new JSoupAdapter().readFrom(bookmark.url)
    metadata match {
      case Success(v) =>
        bm = bm.copy(title = v.title())
        val favicon = v.head().select("link[href~=.*\\.(ico|png)]").first()
        if (favicon != null) {
          bm = bm.copy(favIcon = Some(bookmark.url + favicon.attr("href")))
        }
        bm = bm.copy(hash = Some(generateHash(v)))
      case Failure(f) => // ignore
    }
    bm
  }

  private def generateHash(v: Document) = {
    val md = MessageDigest.getInstance("SHA-1")
    val bytesBuffer = new Array[Byte](1024)
    var bytesRead = -1

//    import java.io.ByteArrayInputStream
//    https://stackoverflow.com/questions/247161/how-do-i-turn-a-string-into-a-inputstreamreader-in-java
//    val inputStream = new ByteArrayInputStream(v.body().toString.getBytes(charset))
//
//    while ( {
//      (bytesRead = inputStream.read(bytesBuffer)) != -1
//    }) digest.update(bytesBuffer, 0, bytesRead)
//
//    val hashedBytes = digest.digest
//
//    return convertByteArrayToHexString(hashedBytes)
    ""
  }

  //def createApplication(app: Bookmark): Future[Option[String]] = ???

  //def getApplication(id: String): Future[Option[Bookmark]] = ???


}

package io.skysail.server.demo.services

import io.skysail.server.adapter.JSoupAdapter
import io.skysail.server.demo.domain.Bookmark
import java.security.MessageDigest

import org.jsoup.nodes.Document
import org.slf4j.LoggerFactory

import scala.util.{Failure, Success, Try}

object BookmarksService {

  private val log = LoggerFactory.getLogger(this.getClass)

  def addMetadata(bookmark: Bookmark) = {
    var bm = bookmark
    //.copy()
    val metadata = new JSoupAdapter().readFrom(bookmark.url).asInstanceOf[Try[Document]]
    metadata match {
      case Success(v) =>
        bm = bm.copy(title = v.title())
        val favicon = v.head().select("link[href~=.*\\.(ico|png)]").first()
        if (favicon != null) {
          bm = bm.copy(favIcon = Some(bookmark.url + favicon.attr("href")))
        }
        bm = bm.copy(hash = Some(generateHash(v)))
      case Failure(f) => log info s"problem getting metadata for ${bookmark.url}"
    }
    bm
  }

  private def convertByteArrayToHexString(hashedBytes: Array[Byte]) = {
    val sb = new StringBuffer()
    for (i <- 0 to hashedBytes.length) {
      sb.append(Integer.toString((hashedBytes(i) & 0xff) + 0x100, 16).substring(1))
    }
    sb.toString
  }

  private def generateHash(v: Document) = {
    val bytes = v.body().toString.getBytes(v.charset())
    String.format("%064x",
      new java.math.BigInteger(1, java.security.MessageDigest.getInstance("SHA-1").digest(bytes)))
  }

}

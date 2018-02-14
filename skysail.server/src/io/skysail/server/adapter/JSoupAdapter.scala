package io.skysail.server.adapter

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.util.{Failure, Success, Try}

class JSoupAdapter {

  def readFrom(url: String): Try[Document] = {
    try {
      Success(Jsoup.connect(url).get)
    } catch {
      //case NonFatal(e) => Failure(e)
      case e: Any => Failure(e)
    }

  }
}

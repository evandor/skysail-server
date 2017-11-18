package io.skysail.server

import java.util.Properties

import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

class SystemPropertiesCommand {

  private val log = LoggerFactory.getLogger(this.getClass)

  def systemProperties(): Unit = {
    log.info("System Properties:")
    log.info("==================")
    val properties: Properties = System.getProperties
    val propertyKeys: Seq[AnyRef] = properties.keys().asScala.toSeq

    propertyKeys
      .map(_.toString)
      .sorted
      .foreach(key =>
        log.info(s"$key => '${properties.get(key)}'")
      )
  }
}
package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity

/**
  * simple configuration element, mapping a key to a string of comma-separated values
  * @param id
  * @param key
  * @param values
  */
case class DbConfig(id: Option[String], key: String, values: String) extends Entity[String]


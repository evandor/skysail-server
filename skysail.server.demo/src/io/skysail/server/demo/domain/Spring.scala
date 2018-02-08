package io.skysail.server.demo.domain

case class SpringBean(
                       bean: String,
                       aliases: List[String],
                       scope: String,
                       `type`: String,
                       resource: String,
                       dependencies: List[String]
                     )

case class SpringBeanList(
                         context: String,
                         parent: String,
                         beans: List[SpringBean]
                         )
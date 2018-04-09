package io.skysail.server.demo.domain

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.ListPayload


case class DockerContainer(
                            id: Option[String],
                            Command: String,
                            Created: Int,
                            Id: String,
                            Image: String,
                            ImageID: String
                          )  extends Entity[String]

case class DockerContainerList(payload: List[DockerContainer]) extends ListPayload[DockerContainer]

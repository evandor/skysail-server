package io.skysail.server.demo.domain

import com.github.dockerjava.api.model.Container

object DockerContainer {
  def apply(c: Container): DockerContainer = {
    DockerContainer(c.getId, c.getImage)
  }

}
case class DockerContainer(id: String, image: String) {

}

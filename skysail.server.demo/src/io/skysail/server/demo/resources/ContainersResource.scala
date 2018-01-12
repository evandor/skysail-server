package io.skysail.server.demo.resources

import com.github.dockerjava.api.model.Container
import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.ListResource
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.DockerContainer

import scala.collection.JavaConverters._

class ContainersResource extends ListResource[DemoApplication, DockerContainer] {
  override def getList(re: RequestEvent): List[DockerContainer] = {
    val list: Seq[Container] = getApplication().dockerClient.listContainersCmd().exec().asScala.toList
    list.map(DockerContainer(_)).toList
  }
}

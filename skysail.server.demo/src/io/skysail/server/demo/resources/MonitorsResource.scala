package io.skysail.server.demo.resources

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.EntityResource
import io.skysail.server.adapter.JSoupAdapter
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.{Monitor, MonitorList}

import scala.util.{Failure, Success}

class MonitorsResource extends EntityResource[DemoApplication, MonitorList]{

  override def getEntity(re: RequestEvent): Option[MonitorList] = {

    var monitorUrls = getApplication().monitorUrls

    val url = "http://website.test.skysail.io"

    val metadata = new JSoupAdapter().readFrom(url)
    metadata match {
      case Success(v) => Some(MonitorList(List(Monitor(Some("id"), "title", "url" ))))
      case Failure(f) => Some(MonitorList(List(Monitor(Some("id"), "broken", "url" ))))
    }
  }

  def toMonitor(url: String): Monitor = {
    val metadata = new JSoupAdapter().readFrom(url)
    metadata match {
      case Success(v) => Monitor(Some("id"), "title", "url" )
      case Failure(f) => Monitor(Some("id"), "broken", "url" )
    }
  }

}

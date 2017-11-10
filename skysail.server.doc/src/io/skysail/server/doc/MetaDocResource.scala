package io.skysail.server.doc

import io.skysail.domain.RequestEvent
import io.skysail.domain.resources.AsyncStaticResource

case class MetaDocResource() extends AsyncStaticResource{

  override def get(requestEvent: RequestEvent): Unit = {
    ???
  }

}

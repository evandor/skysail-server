package io.skysail.db.orientdb.repositories

import io.skysail.domain.model.ApplicationModel
import io.skysail.domain.repositories.RepositoryApi

abstract class HttpRepository[T:Manifest](cls: Class[T], appModel: ApplicationModel)
  extends RepositoryApi[T] {

}

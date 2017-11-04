package io.skysail.domain.app

import io.skysail.domain.model.ApplicationModel

object Application {
  def apply(appModel: ApplicationModel) = {
    new Application(appModel.name, appModel.appPath(), appModel.description)
  }
}

case class Application(name: String, context: String, description: String)

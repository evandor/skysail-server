package io.skysail.domain.app

import io.skysail.domain.model.ApplicationModel

/**
  * Application object.
  */
object Application {
  def apply(appModel: ApplicationModel) = {
    new Application(appModel.name, appModel.appPath(), appModel.description)
  }
}

/**
  * An application descriptor.
  *
  * @param name the application's name
  * @param context the context path for the application
  * @param description a short description of the application
  */
case class Application(name: String, context: String, description: String)

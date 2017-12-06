package io.skysail.messaging.kafka

import domino.DominoActivator
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  whenBundleActive({
    log info s"OrientDbGraphService Bundle active, waiting for configuration..."

    /*whenConfigurationActive("aws") { conf =>
      log info s"received configuration for ${this.getClass.getName}, applying..."
      log info s"$conf"
      val profileName = conf.getOrElse("awsProfileName", "admin").toString
      val region = conf.getOrElse("awsRegion", "US_EAST_1").toString
      new Repository(profileName, region, "bms")
    }*/


  })


}

package io.skysail.api

import org.osgi.framework.{Bundle, BundleContext}
import org.slf4j.LoggerFactory

class ProductUtils {

  private val log = LoggerFactory.getLogger(this.getClass)

  def getOptionalProducktBundle(context: BundleContext): Option[Bundle] = {
    val productBundleName = System.getProperty(Constants.PRODUCT_BUNDLE_IDENTIFIER)
    log.debug("determined product bundle to be '{}'", productBundleName)
    context.getBundles()
      .filter { b => b.getSymbolicName().equals(productBundleName) }
      .headOption
  }
}

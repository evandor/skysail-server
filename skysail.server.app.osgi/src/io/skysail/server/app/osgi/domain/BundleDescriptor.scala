package io.skysail.server.app.osgi.domain

import org.osgi.framework.Bundle

object BundleDescriptor {

  def apply(b: Bundle): BundleDescriptor = {
    BundleDescriptor(
      b.getBundleId.toString,
      b.getSymbolicName,
      b.getVersion.toString,
      "active",
      0L,
      List(),
      List()
    )
  }
}

case class BundleDescriptor(
                             id: String,
                             symbolicName: String,
                             version: String,
                             state: String,
                             size: Long,
                             registeredServiceIds: List[String],
                             usedServiceIds: List[String]
                           )

case class BundleDescriptorList(bundles: List[BundleDescriptor])
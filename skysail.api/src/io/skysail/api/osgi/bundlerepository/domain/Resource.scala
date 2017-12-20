package io.skysail.api.osgi.bundlerepository.domain

import org.osgi.framework.Version

case class Resource(symbolicName: String, version: Version)

package io.skysail.server.demo.it

import org.junit.BeforeClass
import org.junit.Ignore

object IntegrationTestBase {
  @BeforeClass
  def setup(): Unit = {
    println("waiting for service  for 7500 ms...")
    Thread.sleep(7500)
    println("waited for service - done")
  }
}

@Ignore("need to do this to satisfy the bnd junit runner when using scala")
abstract class IntegrationTestBase {
}
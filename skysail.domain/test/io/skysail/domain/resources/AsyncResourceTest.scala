//package io.skysail.domain.resources
//
//import akka.actor.{ActorSystem, Props}
//import akka.testkit.{TestKit, TestProbe}
//import io.skysail.domain.{ListResponseEvent, RequestEvent}
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FlatSpecLike, Matchers}
//
//@RunWith(classOf[JUnitRunner])
//class AsyncResourceTest(_system: ActorSystem)
//  extends TestKit(_system)
//    with Matchers
//    with FlatSpecLike
//    with BeforeAndAfterAll
//    with BeforeAndAfterEach {
//
//  def this() = this(ActorSystem("AkkaQuickstartSpec"))
//
//  override def afterAll: Unit = {
//    shutdown(system)
//  }
//
//  val art = new AsyncResource() {
//    override def get(requestEvent: RequestEvent) = {
//      println("hier")
//      ListResponseEvent(null, null)
//    }
//  }
//
//  "a" should "b" in {
//    val testProbe = TestProbe()
//    val p: Props = Props.apply(classOf[TestControllerActor])//, testProbe.ref)
//    val ca = system.actorOf(p)
//    val re = RequestEvent(null, ca)
//    val r = art.doGet(re)
//    println(r)
//  }
//
//}
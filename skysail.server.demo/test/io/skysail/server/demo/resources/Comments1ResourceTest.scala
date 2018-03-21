package io.skysail.server.demo.resources


import java.util

import akka.actor.ActorSystem
import akka.testkit.TestKit
import io.skysail.api.persistence.DbService
import io.skysail.domain.RequestEvent
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Comment1
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import scala.collection.JavaConverters._


@RunWith(classOf[JUnitRunner])
class Comments1ResourceTest(_system: ActorSystem)
  extends TestKit(_system) //with org.scalatest.FunSuite with BeforeAndAfterEach {
    with Matchers
    with FlatSpecLike
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  val re: RequestEvent = new RequestEvent(null, null)
  val dbService: DbService = Mockito.mock(classOf[DbService])
  val app: DemoApplication = new DemoApplication(null, dbService, null, null)

  Mockito.when(dbService.findGraphs2(
    Comment1(None, ""),
    "SELECT * from io_skysail_server_demo_domain_Comment1",
    app.appModel))
    .thenReturn(List(Comment1(Some("id"), "c1")))
  //  when(dbService.findGraphs(
  //    classOf[Comment1],
  //    "SELECT * from io_skysail_server_demo_domain_Comment1 where id='abc'"))
  //    .thenReturn(List(Comment1(Some("abc"), "title", "url")))

  var resource: Comments1Resource = null

  def this() = this(ActorSystem("AkkaQuickstartSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  override def beforeEach() = {
    resource = new Comments1Resource()
    resource.setApplication(app)
  }

  //  "the listResource" should "return data from the repository" in {
  //    val res = bsmr.getList(re).iterator.asJava
  //    assertThat(res).containsOnlyOnce(Comment1(Some("id"), "title", "url"))
  //  }

  "a getList request" should "return nonNull response event" in {
    val r: util.List[Comment1] = resource.getList(re).payload.asJava
    org.assertj.core.api.Assertions.assertThat(r).hasSize(1)
  }


  //  "a post request on postResource" should "return nonNull response event" in {
  //    val p: Props = Props.apply(classOf[ControllerActor])//, testProbe.ref)
  //    val ca = system.actorOf(p)
  //    var ctx = Mockito.mock(classOf[RequestContext])
  //    var request = HttpRequest() //Mockito.mock(classOf[HttpRequest])
  //    when(ctx.request).thenReturn(request)
  //    val entity = Comment1(Some("id"), "title","url")
  //    val re: RequestEvent = new RequestEvent(ProcessCommand(ctx, null, null, null, null, entity), ca)
  //    val res: Unit = postBmr.post(re)
  //    verify(dbService).persist(entity, app.appModel.puml)
  //  }
  //
  //
  //  "a get request on putResource" should "return nonNull response event" in {
  //    val parameter: scala.List[_root_.scala.Predef.String] = List("abc")
  //    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, parameter, null, "entity"), null)
  //    assertThat(putBmr.get(re)).isNotNull
  //  }
  //
  //  "a put request on putResource" should "return nonNull response event" in {
  //    var Comment1 = Comment1(Some("abc"), "new", "value")
  //    val ca = system.actorOf(Props.apply(classOf[ControllerActor]))
  //    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, List("abc"), null, Comment1), ca)
  //    val res: Unit = putBmr.put(re)
  //    verify(dbService).persist(Comment1, app.appModel.puml)
  //  }

}

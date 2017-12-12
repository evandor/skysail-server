package io.skysail.server.demo.resources

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestKit
import io.skysail.api.persistence.DbService
import io.skysail.domain.RequestEvent
import io.skysail.domain.app.ApplicationApi
import io.skysail.domain.messages.ProcessCommand
import io.skysail.server.actors.ControllerActor
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Bookmark
import org.assertj.core.api.Assertions._
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class BookmarksResourceTest(_system: ActorSystem)
  extends TestKit(_system) //with org.scalatest.FunSuite with BeforeAndAfterEach {
    with Matchers
    with FlatSpecLike
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  //val a =  Mockito.mock(classOf[ControllerActor[Bookmark]])
  val re: RequestEvent = new RequestEvent(null, null)
  //Mockito.mock(classOf[EmptyLocalActorRef]))
  val dbService: DbService = Mockito.mock(classOf[DbService])
  val app: ApplicationApi = new DemoApplication(null, dbService, null)

  when(dbService.findGraphs(
    classOf[Bookmark],
    "SELECT * from io_skysail_server_demo_domain_Bookmark"))
    .thenReturn(List(Bookmark(Some("id"), "title", "url")))
  when(dbService.findGraphs(
    classOf[Bookmark],
    "SELECT * from io_skysail_server_demo_domain_Bookmark where id='abc'"))
    .thenReturn(List(Bookmark(Some("abc"), "title", "url")))

  var bsmr: BookmarksResource = null
  var postBmr: PostBookmarkResource = null
  var putBmr: PutBookmarkResource = null

  def this() = this(ActorSystem("AkkaQuickstartSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  override def beforeEach() = {
    bsmr = new BookmarksResource()
    bsmr.setApplication(app)
    postBmr = new PostBookmarkResource()
    postBmr.setApplication(app)
    putBmr = new PutBookmarkResource()
    putBmr.setApplication(app)
  }

  "the listResource" should "return data from the repository" in {
    val res = bsmr.getList(re).iterator.asJava
    assertThat(res).containsOnlyOnce(Bookmark(Some("id"), "title", "url"))
  }

  "a get request on postResource" should "return nonNull response event" in {
    assertThat(postBmr.get(re)).isNotNull
  }

  "a post request on postResource" should "return nonNull response event" in {
    val p: Props = Props.apply(classOf[ControllerActor[String]])//, testProbe.ref)
    val ca = system.actorOf(p)
    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, null, null, "entity"), ca)
    val res: Unit = postBmr.post(re)
    verify(dbService).persist("entity")
  }


  "a get request on putResource" should "return nonNull response event" in {
    val parameter: scala.List[_root_.scala.Predef.String] = List("abc")
    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, parameter, null, "entity"), null)
    assertThat(putBmr.get(re)).isNotNull
  }

  "a put request on putResource" should "return nonNull response event" in {
    var bookmark = Bookmark(Some("abc"), "new", "value")
    val ca = system.actorOf(Props.apply(classOf[ControllerActor[String]]))
    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, List("abc"), null, bookmark), ca)
    val res: Unit = putBmr.put(re)
    verify(dbService).persist(bookmark)
  }

}

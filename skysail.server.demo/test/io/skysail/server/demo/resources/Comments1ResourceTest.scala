package io.skysail.server.demo.resources


import akka.actor.ActorSystem
import akka.testkit.TestKit
import io.skysail.db.orientdb.repositories.ResourceRepository
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.Comment1
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class Comments1ResourceTest(_system: ActorSystem)
  extends TestKit(_system)
    with Matchers
    with FlatSpecLike
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  val re: RequestEvent = Mockito.mock(classOf[RequestEvent])
  val app: DemoApplication = Mockito.mock(classOf[DemoApplication])
  var repo: ResourceRepository[Comment1] = Mockito.mock(classOf[ResourceRepository[Comment1]])

  val c1 = Comment1(Some("id"), "c1")

  when(app.comments1Repo).thenReturn(repo)
  when(repo.find()).thenReturn(List(c1))
  when(repo.find("id")).thenReturn(Some(c1))
  when(repo.save(c1)).thenReturn("createdId")

  var resource: Comments1Resource = null

  def this() = this(ActorSystem("AkkaQuickstartSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  override def beforeEach() = {
    resource = new Comments1Resource()
    resource.setApplication(app)
  }

  "a getList request" should "return the expected payload" in {
    val r: Seq[Comment1] = resource.getList(re).payload
    r should have length 1
    r should contain (Comment1(Some("id"), "c1"))
  }

  "a getEntity request" should "return the expected payload" in {
    when(re.firstParam).thenReturn("id")
    val r: Option[Comment1] = resource.getEntity(re)
    r shouldBe Some(c1)
  }

  "the template" should "return a comment1 without id" in {
    when(re.firstParam).thenReturn("id")
    val r: Comment1 = resource.getTemplate(re)
    r.id shouldBe None
  }

  "a createEntity request" should "return the expected payload" in {
    var cmd: ProcessCommand = Mockito.mock(classOf[ProcessCommand])
    Mockito.when(cmd.entity).thenReturn(c1, Nil: _*)
    when(re.cmd).thenReturn(cmd)
    val r: String = resource.createEntity(re)
    r shouldBe "createdId"
  }

  "an updateEntity request" should "return the expected payload" in {
    var cmd: ProcessCommand = Mockito.mock(classOf[ProcessCommand])
    Mockito.when(cmd.entity).thenReturn(c1, Nil: _*)
    when(re.cmd).thenReturn(cmd)
    when(re.firstParam).thenReturn("id")
    resource.updateEntity(re)
    //r shouldBe "createdId"
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

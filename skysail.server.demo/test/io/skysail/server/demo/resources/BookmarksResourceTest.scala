package io.skysail.server.demo.resources

import io.skysail.api.persistence.DbService
import io.skysail.domain.RequestEvent
import io.skysail.domain.app.ApplicationApi
import io.skysail.server.demo.DemoApplication

import scala.collection.mutable.Stack

class BookmarksResourceTest extends org.scalatest.FunSuite  {

  //override def beforeEach() = {
    //    app = new SkysailRootApplication() {}
    //    SkysailApplication.serviceListProvider = Mockito.mock(classOf[ScalaServiceListProvider])
    //    val appService = Mockito.mock(classOf[SkysailApplicationService])
    //    Mockito.when(app.getSkysailApplicationService()).thenReturn(appService)
    //    val appContextResource = Mockito.mock(classOf[ResourceModel])
    //    Mockito.when(appService.getApplicationContextResources()).thenReturn(List(appContextResource))
    //    org.restlet.Application.setCurrent(app)
    //  }

  val re: RequestEvent = new RequestEvent(null,null)

  val dbService: DbService = Mockito.mock(classOf[DbService])

  val app: ApplicationApi = new DemoApplication(null,dbService)

  test("pop is invoked on a non-empty stack") {

    val bsm = new BookmarksResource()
    bsm.setApplication(app)
    val res = bsm.getList(re)


    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    val oldSize = stack.size
    val result = stack.pop()
    assert(result === 2)
    assert(stack.size === oldSize - 1)
  }

  test("pop is invoked on an empty stack") {

    val emptyStack = new Stack[Int]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
    assert(emptyStack.isEmpty)
  }
}

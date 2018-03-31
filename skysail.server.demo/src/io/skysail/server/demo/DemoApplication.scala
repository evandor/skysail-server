package io.skysail.server.demo

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{getFromResourceDirectory, pathPrefix}
import akka.http.scaladsl.server.PathMatchers._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.api.persistence.DbService
import io.skysail.db.orientdb.repositories.ResourceRepository
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import io.skysail.server.demo.domain.{Comment1, Comment2, DbConfig, Monitor2}
import io.skysail.server.demo.repositories._
import io.skysail.server.demo.resources.{DbConfigsResource, _}
import io.skysail.server.demo.services.{BookmarkSchedulerService, EventService}
import org.osgi.framework.BundleContext
import org.osgi.service.event.EventAdmin

import scala.concurrent.ExecutionContextExecutor
import io.skysail.server.demo.resources.PatternsResource

class DemoApplication(
                       bundleContext: BundleContext,
                       dbService: DbService,
                       system: ActorSystem,
                       routesCreator: RoutesCreatorTrait) extends BackendApplication(bundleContext, routesCreator, system) with ApplicationProvider {

  var monitorUrls: Iterable[String] = null

  def setMonitorUrls(strings: Iterable[String]): Unit = {
    monitorUrls = strings
  }

  var eventService: EventService = new EventService(null)

  def setEventAdmin(eventAdmin: EventAdmin) = {
    eventService = new EventService(eventAdmin)
  }

  val repo = new BookmarksRepository(dbService, appModel)
  val dbConfigRepo = new DbConfigsRepository(dbService, appModel)
  val notesRepo = new NotesRepo(dbService, appModel)
  val todosRepo = new TodosRepo(dbService, appModel)
  val accountsRepo = new AccountsRepo(dbService, appModel)
  val patternRepo = new PatternRepository(dbService, appModel)
  //val comments1Repo = new Comments1Repository(dbService, appModel)
  val monitor2Repo = new MonitorRepository(classOf[Monitor2], appModel)

  val comments1Repo = new ResourceRepository(classOf[Comment1], dbService, appModel) {
    override def getEntityTemplate = Comment1(None, "")
  }

  val comments2Repo = new ResourceRepository(classOf[Comment2], dbService, appModel) {
    override def getEntityTemplate() = Comment2(None, "")
  }

  if (system != null) {
    BookmarkSchedulerService.checkBookmarks(system)
  }
  //val dockerClient = DockerClientBuilder.getInstance().build()

  override def name = "demo"

  override def desc = "Skysail Demo Application"

  override def defaultResources = List(
    classOf[DbConfigsResource],
    classOf[NotesResource],
    classOf[TodosResource],
    classOf[AccountsResource],
    classOf[Comment1Resource],
    classOf[Comment2Resource],
    classOf[Monitor2Resource]
  )

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("demo") / PathMatcher("v1")
    List(
      RouteMapping("", root ~ PathEnd, classOf[BookmarksResource]),

      RouteMapping("/bms", root / PathMatcher("bms") ~ PathEnd, classOf[BookmarksResource]),
      RouteMapping("/bms/", root / PathMatcher("bms") / PathEnd, classOf[PostBookmarkResource]),
      RouteMapping("/bms/:id", root / PathMatcher("bms") / Segment ~ PathEnd, classOf[BookmarkResource]),
      RouteMapping("/bms/:id/", root / PathMatcher("bms") / Segment / PathEnd, classOf[PutBookmarkResource]),

      //RouteMapping("/docker/container", root / PathMatcher("docker") / PathMatcher("container") ~ PathEnd, classOf[ContainersResource]),

      RouteMapping("/patterns", root / PathMatcher("patterns") ~ PathEnd, classOf[PatternsResource]),
      RouteMapping("/patterns/", root / PathMatcher("patterns") / PathEnd, classOf[PostPatternResource]),
      RouteMapping("/patterns/:id", root / PathMatcher("patterns") / Segment ~ PathEnd, classOf[PatternResource]),
      RouteMapping("/patterns/:id/", root / PathMatcher("patterns") / Segment / PathEnd, classOf[PutPatternResource]),

      RouteMapping("/beans", root / PathMatcher("beans") ~ PathEnd, classOf[BeansResource]),

      RouteMapping("/monitor", root / PathMatcher("monitor") ~ PathEnd, classOf[MonitorsResource]),

      RouteMapping("/es/indices", root / PathMatcher("es") / PathMatcher("indices") ~ PathEnd, classOf[IndicesResource]))
  }

  def getList(key: String): List[String] = {
    val dbConfig = dbConfigRepo.find().filter(config => config.key.equals(key)).headOption.getOrElse(DbConfig(None, "", ""))
    dbConfig.values.split(",").toList.map(_.trim())
  }

  def get(key: String): String = {
    val dbConfig = dbConfigRepo.find().filter(config => config.key.equals(key)).headOption.getOrElse(DbConfig(None, "", ""))
    dbConfig.values
  }

  override def nativeRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("") {
      akka.http.scaladsl.server.Directives.get {
        getFromResourceDirectory("assets", this.getClass.getClassLoader)
      }
    }
  }

}
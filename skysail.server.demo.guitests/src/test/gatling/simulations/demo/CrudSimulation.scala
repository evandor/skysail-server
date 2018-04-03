package demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CrudSimulation extends Simulation {

  object HomePage {

    val homepage = exec(
      http("root").get("/")
        .check(status.is(200))
        .check(currentLocationRegex(".*demo/v1")))
      .pause(1)
  }

  object Comment1Page {

    val get = exec(
      http("get post form").get("/demo/v1/comment1s")
        .check(status.is(200))
    ).pause(1)

    val post = exec(
      http("create comment")
        .post("/demo/v1/comment1s/")
        .headers(headers_10)
        .formParam("comment", "a test comment")).pause(1)
  }


  val baseUrl = sys.props.getOrElse("gatling.baseUrl", "http://127.0.0.1:9403")

  val httpConf = http
    .baseURL(baseUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers_10 = Map("Content-Type" -> "application/x-www-form-urlencoded") // Note the headers specific to a given request

  val scn = scenario("redirect from root page")
    .exec(
      http("root").get("/")
        .check(status.is(200))
        .check(currentLocationRegex(".*demo/v1"))
    )
    .pause(1)




  //setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
  //  .assertions(forAll.failedRequests.percent.lte(5))

  val users = scenario("CRUD").exec(HomePage.homepage, Comment1Page.get, Comment1Page.post)
  //val admins = scenario("Admins").exec(Search.search, Browse.browse, Edit.edit)

  setUp(
    users.inject(rampUsers(1) over (1 seconds))
    //admins.inject(rampUsers(2) over (10 seconds))
  ).protocols(httpConf).assertions(forAll.failedRequests.percent.lte(1))
}
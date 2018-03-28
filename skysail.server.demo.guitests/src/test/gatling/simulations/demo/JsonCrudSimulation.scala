package demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class JsonCrudSimulation extends Simulation {

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
    .acceptHeader("application/json")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .extraInfoExtractor(ExtraInfo => {
      println("httpCode: " + ExtraInfo.response.statusCode.getOrElse(0) +
        "\nResponse: " + ExtraInfo.response.body.string); List(ExtraInfo.response.statusCode, ExtraInfo.response.body.string)
    })


  val headers_10 = Map("Content-Type" -> "application/x-www-form-urlencoded") // Note the headers specific to a given request

  val users = scenario("JSON CRUD").exec(HomePage.homepage)

  setUp(
    users.inject(rampUsers(1) over (1 seconds))
  ).protocols(httpConf).assertions(forAll.failedRequests.percent.lte(1))
}

package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Basic2Simulation extends Simulation {

  val baseUrl = sys.props.getOrElse("gatling.baseUrl", "http://demo.test.skysail.io")

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
      http("request_1").get("/")
        .check(status.is(200))
        .check(currentLocationRegex(".*demo/v1"))
    )
    .pause(1)
    //.exec(http("request_2").get("/computers?f=macbook"))
    //.pause(1)
    //.exec(http("request_3").get("/computers/6"))

  setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
    .assertions(forAll.failedRequests.percent.lte(5))
}

package simpleSimulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class DemosSearchTerm extends Simulation{
  val httpConf = http.baseUrl("https://demos.bellatrix.solutions")
  val feeder = csv("./src/test/resources/data/searchTerms.csv").circular

  val scn = scenario("Search Term")
    .repeat(4){
      feed(feeder)
        .exec(http("open home")
          .get("/")
          .check(status.is(200)))
        .pause(1)
        .exec(http("search saturn")
          .get("/?s=${term}&post_type=product")
          .check(status.is(200))
          .check(substring("${result}")))
    }

  setUp(
    scn.inject(
      atOnceUsers(100),
    ).protocols(httpConf)
  )
}

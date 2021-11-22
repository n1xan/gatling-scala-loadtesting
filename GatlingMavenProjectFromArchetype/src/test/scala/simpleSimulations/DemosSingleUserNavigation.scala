package simpleSimulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class DemosSingleUserNavigation extends Simulation{
  val httpConf = http
//    .proxy(Proxy("localhost", 8888))
    .baseUrl("https://demos.bellatrix.solutions")

  val scn = scenario("Navigate")
    .exec(http("get home page")
    .get("/")
    .check(status.is(200)))

  setUp(scn.inject(
    atOnceUsers(10)
  )).protocols(httpConf)
}

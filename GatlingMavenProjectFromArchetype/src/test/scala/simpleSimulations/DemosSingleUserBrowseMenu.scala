package simpleSimulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.language.postfixOps
import scala.concurrent.duration.DurationInt
import scala.util.Random

class DemosSingleUserBrowseMenu extends Simulation{
  val httpConf = http.baseUrl("https://demos.bellatrix.solutions")

  val scn = scenario("Navigate")
    .exec(http("Get Home page")
      .get("/")
      .check(status.is(200)))

    .pause(Random.nextInt(5))
    .exec(http("Get Blog page")
      .get("/blog/")
      .check(status.is(200)))

    .pause(Random.nextInt(5))
    .exec(http("Get MyAccount page")
      .get("/my-account/")
      .check(status.is(200)))

    .pause(Random.nextInt(5))
    .exec(http("Get Promotions page")
      .get("/welcome/")
      .check(status.is(200)))

  setUp(scn.inject(atOnceUsers(10))).protocols(httpConf)
}

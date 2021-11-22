package mixedSimulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.util.Random

class BellatrixDemosScenarios {
  val feeder = csv("./src/test/resources/data/searchTerms.csv").circular

  val searchScenario = scenario("Search Term")
    .repeat(4){
      feed(feeder)
        .exec(http("Open home")
          .get("/")
          .check(status.is(200)))
        .pause(1)
        .exec(http("Search term")
          .get("/?s=${term}&post_type=product")
          .check(status.is(200))
          .check(substring("${result}")))
    }

  val userMenuNavigationScenario = scenario("Navigate on Menu Items")
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

  val homePageNavigationScenario = scenario("Navigate to Home")
    .exec(http("Get home page")
      .get("/")
      .check(status.is(200)))
}

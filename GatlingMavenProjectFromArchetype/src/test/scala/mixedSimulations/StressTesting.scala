package mixedSimulations

import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class StressTesting extends BaseBellatrixSimulation {
  val runDuration = 1.minutes
  val startUsers = 10
  val maxUsers = 100

  setUp(scenarios.searchScenario.inject(
    rampConcurrentUsers(startUsers) to maxUsers during runDuration
  )).protocols(httpConf);
}

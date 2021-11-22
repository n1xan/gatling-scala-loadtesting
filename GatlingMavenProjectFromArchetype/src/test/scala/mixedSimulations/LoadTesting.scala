package mixedSimulations

import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class LoadTesting extends BaseBellatrixSimulation {

  val runDuration = 1.minutes
  setUp(
    scenarios.searchScenario.inject(
      constantConcurrentUsers(5) during runDuration
  ),
    scenarios.homePageNavigationScenario.inject(
      constantConcurrentUsers(10) during runDuration
    ),
    scenarios.userMenuNavigationScenario.inject(
      constantConcurrentUsers(8) during runDuration
    )).protocols(httpConf)
}

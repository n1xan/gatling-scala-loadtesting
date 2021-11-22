package mixedSimulations

import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SpikeTesting extends BaseBellatrixSimulation {
  val runDuration = 1.minutes
  val minUsersLoad = 1
  val normalUsersLoad = 10
  val spikeUsersLoad = 100
  val rampTime = runDuration/10
  val initCoolDownTime = 10.seconds

  setUp(
    scenarios.homePageNavigationScenario.inject(
      constantConcurrentUsers(normalUsersLoad) during runDuration
    ),
    scenarios.userMenuNavigationScenario.inject(
      constantConcurrentUsers(normalUsersLoad) during runDuration
    ),
    scenarios.searchScenario.inject(
      constantConcurrentUsers(minUsersLoad) during initCoolDownTime,
      rampConcurrentUsers(minUsersLoad) to spikeUsersLoad during rampTime,
      rampConcurrentUsers(spikeUsersLoad) to normalUsersLoad during rampTime,
      rampConcurrentUsers(minUsersLoad) to spikeUsersLoad during rampTime,
      rampConcurrentUsers(spikeUsersLoad) to normalUsersLoad during initCoolDownTime,
  )).protocols(httpConf)
}


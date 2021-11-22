package mixedSimulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class BaseBellatrixSimulation extends Simulation{
  val httpConf = http.baseUrl("https://demos.bellatrix.solutions")
//    .proxy(Proxy("localhost", 8888)
  //    .disableCaching

  val scenarios = new BellatrixDemosScenarios();

}

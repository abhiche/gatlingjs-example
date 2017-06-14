/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoginSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:3000") // Here is the root for all relative URLs

  val loginScenario = scenario("Login") // A scenario is a chain of requests and pauses

    .exec(http("Login API") // Here's an example of a POST request
      .post("/api/login")
      .formParam("username", "username")
      .formParam("password", "password"))

  setUp(loginScenario.inject(atOnceUsers(100)).protocols(httpConf))
}

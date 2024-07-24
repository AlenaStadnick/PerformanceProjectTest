package simulations;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LoadSimulation extends Simulation {

    // Load configuration
    private static final Config config = ConfigFactory.load("application.conf");

    // Use configuration values
    private static final String baseUrl = config.getString("gatling.http.baseUrl");
    private static final String authToken = config.getString("gatling.http.authToken");

    HttpProtocolBuilder httpProtocol = http
            .baseUrl(baseUrl)
            .header("Authorization", "Bearer " + authToken);

    ScenarioBuilder loadScenario1 = scenario("Load Test Scenario 1")
            .exec(
                    http("Get Authorized User")
                            .get("/user")
                            .check(status().is(200))
                            .check(jsonPath("$.user.id").exists())
                            .check(jsonPath("$.user.username").exists())
                            .check(jsonPath("$.user.email").exists())
            )
            .exec(session -> {
                System.out.println("Scenario 1: 10 users/sec for 20 sec, 100 users/sec for 5 sec, 10 users/sec for 20 sec");
                return session;
            });

    ScenarioBuilder loadScenario2 = scenario("Load Test Scenario 2")
            .exec(
                    http("Get Authorized User")
                            .get("/user")
                            .check(status().is(200))
                            .check(jsonPath("$.user.id").exists())
                            .check(jsonPath("$.user.username").exists())
                            .check(jsonPath("$.user.email").exists())
            )
            .exec(session -> {
                System.out.println("Scenario 2: 20 users/sec for 20 sec, ramp up to 100 users/sec for 30 sec");
                return session;
            });

    {
        setUp(
                loadScenario1.injectOpen(
                        nothingFor(5), // wait 5 seconds before starting scenario 1
                        constantUsersPerSec(10).during(20), // 10 users/sec for 20 sec
                        rampUsers(100).during(5), // ramp up to 100 users in 5 sec
                        constantUsersPerSec(10).during(20) // 10 users/sec for another 20 sec
                ).protocols(httpProtocol),

                loadScenario2.injectOpen(
                        nothingFor(5), // wait 5 seconds before starting scenario 2
                        constantUsersPerSec(20).during(20), // 20 users/sec for 20 sec
                        rampUsers(100).during(30) // ramp up to 100 users in 30 sec
                ).protocols(httpProtocol)
        ).assertions(
                global().successfulRequests().percent().is(100.0)
        );
    }
}

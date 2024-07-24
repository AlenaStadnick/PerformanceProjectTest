package simulations;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.core.CoreDsl.*;
import io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LoadSimulation extends Simulation {

    // Load configuration
    private static final Config config = ConfigFactory.load();

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
                        constantUsersPerSec(10).during(20),
                        constantUsersPerSec(100).during(5),
                        constantUsersPerSec(10).during(20)
                ).protocols(httpProtocol),

                loadScenario2.injectOpen(
                        constantUsersPerSec(20).during(20),
                        rampUsersPerSec(20).to(100).during(30)
                ).protocols(httpProtocol)
        ).assertions(
                global().successfulRequests().percent().is(100.0)
        );
    }
}

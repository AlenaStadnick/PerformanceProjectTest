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

public class ApiSimulation extends Simulation {

    // Load configuration
    private static final Config config = ConfigFactory.load();

    // Use configuration values
    private static final String baseUrl = config.getString("gatling.http.baseUrl");
    private static final String authToken = config.getString("gatling.http.authToken");

    HttpProtocolBuilder httpProtocol = http
            .baseUrl(baseUrl)
            .header("Authorization", "Bearer " + authToken);

    ScenarioBuilder scn = scenario("Get Authorized User Scenario")
            .exec(
                    http("Get Authorized User")
                            .get("/user")
                            .header("Authorization", "Bearer " + authToken)
                            .check(status().is(200))
                            .check(jsonPath("$.user.id").exists())
                            .check(jsonPath("$.user.username").exists())
                            .check(jsonPath("$.user.email").exists())
                            .check(jsonPath("$.user.color").exists())
                            .check(jsonPath("$.user.profilePicture").exists())
                            .check(jsonPath("$.user.initials").exists())
                            .check(jsonPath("$.user.week_start_day").exists())
                            .check(jsonPath("$.user.global_font_support").exists())
                            .check(jsonPath("$.user.timezone").exists())
            )
            .exec(session -> {
                System.out.println("API Test: Get Authorized User executed");
                return session;
            });

    {
        setUp(scn.injectOpen(atOnceUsers(1)).protocols(httpProtocol));
    }
}

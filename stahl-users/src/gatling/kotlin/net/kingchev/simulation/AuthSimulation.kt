package net.kingchev.simulation

import io.gatling.javaapi.core.CoreDsl.StringBody
import io.gatling.javaapi.core.CoreDsl.constantUsersPerSec
import io.gatling.javaapi.core.CoreDsl.feed
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.CoreDsl.stressPeakUsers
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import net.datafaker.Faker
import net.datafaker.providers.base.Text
import net.datafaker.providers.base.Text.DIGITS
import net.datafaker.providers.base.Text.EN_UPPERCASE
import java.util.stream.Stream
import kotlin.collections.mapOf

class AuthSimulation : Simulation() {
    val faker = Faker()
    val feeder: Iterator<Map<String, String>> = Stream.generate {
        return@generate mapOf(
            "name" to faker.name().fullName(),
            "username" to faker.name().username(),
            "email" to faker.domain().fullDomain("@gmail.com"),
            "password" to faker.text().text(
                Text.TextSymbolsBuilder.builder().len(8)
                .with(EN_UPPERCASE, 2)
                .with(DIGITS, 3)
                .build()
            )
        )
    }.iterator()

    val httpProtocol = http.baseUrl("http://localhost:8081")
        .acceptHeader("application/json")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");

    val scenario = scenario("Scenario")
        .exec(
            feed(feeder),
            http("Register")
                .post("/auth/register")
                .header("Content-Type", "application/json")
                .body(StringBody(
                    """
                        {
                            "name": "#{name}",
                            "username": "#{username}",
                            "email": "#{email}",
                            "password": "#{password}"
                        }
                    """.trimIndent()
                )),
            http("Login")
                .get("/auth/login")
                .header("Content-Type", "application/json")
                .body(StringBody(
                    """
                        {
                            "username": "#{username}",
                            "password": "#{password}"
                        }
                    """.trimIndent()
                ))
        )

    init {
        setUp(
            scenario.injectOpen(
                constantUsersPerSec(500.0)
                    .during(20)
            )
        ).protocols(httpProtocol);
    }
}
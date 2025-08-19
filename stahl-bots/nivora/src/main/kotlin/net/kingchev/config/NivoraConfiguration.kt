package net.kingchev.config

import dev.kord.gateway.Intent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.kingchev.Nivora
import net.kingchev.NivoraBotApplication
import net.kingchev.NivoraClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(NivoraProperties::class)
public class NivoraConfiguration(private val props: NivoraProperties) {
    @Bean
    public fun nivoraClient(): NivoraClient {
        val client = Nivora(props.token)

        GlobalScope.launch {
            client.login() {
                Intent.entries.forEach { intents += it }
                logger.info("Bot was started")
            }
        }

        return client
    }

    public companion object {
        public val logger: Logger = LoggerFactory.getLogger(NivoraBotApplication::class.java)
    }
}
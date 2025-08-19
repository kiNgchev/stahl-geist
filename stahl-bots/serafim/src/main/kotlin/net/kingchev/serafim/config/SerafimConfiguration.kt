package net.kingchev.serafim.config

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.philippheuer.events4j.simple.SimpleEventHandler
import net.kingchev.serafim.SerafimBotApplication
import net.kingchev.serafim.SerafimClient
import net.kingchev.serafim.SerafimClientBuilder
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties(value = [SerafimProperties::class, SerafimProperties.Tokens::class])
public class SerafimConfiguration(private val props: SerafimProperties) {
    @Bean
    public fun simpleEventHandler(client: SerafimClient): SimpleEventHandler =
        client
            .eventManager
            .getEventHandler(SimpleEventHandler::class.java)

    @Bean
    public fun serafimClient(): SerafimClient {
        val client = SerafimClientBuilder.builder()
            .withChatAccount(OAuth2Credential("twitch", props.tokens.irc))
            .withEnableChat(true)
            .withClientId(props.tokens.clientId)
            .withClientSecret(props.tokens.clientSecret)
            .withEnableHelix(true)
            .build()

        props.twitchChannel.forEach {
            client.chat.joinChannel(it)
            logger.info("Serafim joined to $it ")
        }

        client.clientHelper.enableStreamEventListener(props.twitchChannel)
        return client
    }

    public companion object {
        private val logger = LoggerFactory.getLogger(SerafimBotApplication::class.java)
    }
}
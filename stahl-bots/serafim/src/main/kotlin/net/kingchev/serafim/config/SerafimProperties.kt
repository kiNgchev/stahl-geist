package net.kingchev.serafim.config

import org.springframework.boot.context.properties.ConfigurationProperties

private const val PREFIX = "serafim"

@ConfigurationProperties(prefix = PREFIX)
public class SerafimProperties {
    public var tokens: Tokens = Tokens()
    public var twitchChannel: List<String> = mutableListOf()
    public var username: String? = null

    @ConfigurationProperties(prefix = "${PREFIX}.tokens")
    public class Tokens {
        public var clientId: String? = null
        public var clientSecret: String? = null
        public var irc: String? = null
    }
}
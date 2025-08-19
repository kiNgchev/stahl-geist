package net.kingchev.zentra.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "zentra")
public data class ZentraProperties(
    val token: String,
    val username: String = "zentra_stahl_bot"
)
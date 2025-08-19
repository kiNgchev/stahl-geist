package net.kingchev.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.context.annotation.Import

@EnableConfigServer
@SpringBootApplication
@Import(SecurityConfiguration::class)
public class StahlConfigServerApplication

public fun main(args: Array<String>) {
    runApplication<StahlConfigServerApplication>(*args)
}

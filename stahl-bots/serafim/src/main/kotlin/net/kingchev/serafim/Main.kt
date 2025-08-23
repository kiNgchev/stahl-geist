package net.kingchev.serafim

import net.kingchev.bots.shared.config.KafkaConfiguration
import net.kingchev.serafim.config.SerafimConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(SerafimConfiguration::class, KafkaConfiguration::class)
public class SerafimBotApplication

public fun main(args: Array<String>) {
    runApplication<SerafimBotApplication>(*args)
}
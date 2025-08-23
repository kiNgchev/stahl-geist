package net.kingchev

import net.kingchev.bots.shared.config.KafkaConfiguration
import net.kingchev.config.NivoraConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(NivoraConfiguration::class, KafkaConfiguration::class)
public class NivoraBotApplication

public fun main() {
    runApplication<NivoraBotApplication>()
}
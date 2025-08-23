package net.kingchev.cdn

import net.kingchev.cdn.config.ResourceConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ResourceConfiguration::class)
public class StahlResourceApplication

public fun main(args: Array<String>) {
    runApplication<StahlResourceApplication>(*args)
}
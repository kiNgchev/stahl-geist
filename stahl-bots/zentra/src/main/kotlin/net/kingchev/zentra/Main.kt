package net.kingchev.zentra

import net.kingchev.zentra.config.ZentraConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(scanBasePackages = ["net.kingchev.zentra"])
@Import(ZentraConfig::class)
public class ZentraBotApplication

public fun main(args: Array<String>) {
    runApplication<ZentraBotApplication>(*args)
}
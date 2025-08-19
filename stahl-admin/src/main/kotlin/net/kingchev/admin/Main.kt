package net.kingchev.admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableAdminServer
@SpringBootApplication
public class StahlAdminServerApplication

public fun main(args: Array<String>) {
    runApplication<StahlAdminServerApplication>(*args)
}
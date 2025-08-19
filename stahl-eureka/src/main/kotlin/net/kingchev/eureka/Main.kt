package net.kingchev.eureka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
public class StahlEurekaServerApplication

public fun main(args: Array<String>) {
    runApplication<StahlEurekaServerApplication>(*args)
}
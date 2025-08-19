package net.kingchev.users

import net.kingchev.users.config.UsersConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Import

@EnableDiscoveryClient
@SpringBootApplication
@Import(UsersConfiguration::class)
public class StahlUsersServiceApplication

public fun main(args: Array<String>) {
    runApplication<StahlUsersServiceApplication>(*args)
}
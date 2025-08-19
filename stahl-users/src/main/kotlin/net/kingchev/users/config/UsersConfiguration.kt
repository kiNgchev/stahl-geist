package net.kingchev.users.config

import net.kingchev.entity.Authority
import net.kingchev.model.Role
import net.kingchev.users.repository.AuthorityRepository
import net.kingchev.users.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["net.kingchev"])
@EnableJpaRepositories(basePackages = ["net.kingchev.users.repository"])
@ComponentScan(basePackages = ["net.kingchev.users"])
@Import(SecurityConfiguration::class)
public class UsersConfiguration(
    private val userService: UserService,
    private val repository: AuthorityRepository
) {
    @Bean
    public fun registerAuthorities(): CommandLineRunner {
        return CommandLineRunner {
            Role.entries.forEach {
                repository.save(Authority(it))
            }
        }
    }
}
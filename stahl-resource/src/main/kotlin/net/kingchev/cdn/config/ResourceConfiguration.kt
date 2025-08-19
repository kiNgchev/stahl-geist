package net.kingchev.cdn.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EntityScan(basePackages = ["net.kingchev"])
@EnableJpaRepositories(basePackages = ["net.kingchev.cdn.repository"])
@ComponentScan(basePackages = ["net.kingchev.cdn"])
public class ResourceConfiguration {
    @Bean
    public fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
        }

        return http.build()
    }
}
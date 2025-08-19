package net.kingchev.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/**", hasRole("SERVICE"))
                authorize(anyRequest, authenticated)
            }
            csrf { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            httpBasic {  }
        }
        return http.build()
    }
}
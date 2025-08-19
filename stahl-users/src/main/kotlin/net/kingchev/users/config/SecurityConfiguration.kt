package net.kingchev.users.config

import net.kingchev.model.Role
import net.kingchev.users.security.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
public class SecurityConfiguration(private val jwtFilter: JwtFilter) {
    @Bean
    public fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/auth/**", permitAll)
                authorize("/api/users/manage/**", hasAnyAuthority(Role.ROLE_SERVICE.name, Role.ROLE_ADMIN.name))
                authorize(anyRequest, authenticated)
            }
            csrf { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtFilter)
            httpBasic { }
        }

        return http.build()
    }

    @Bean
    public fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
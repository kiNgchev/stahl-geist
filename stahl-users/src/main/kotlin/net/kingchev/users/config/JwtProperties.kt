package net.kingchev.users.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
@ConfigurationProperties(prefix = "stahl.jwt")
public class JwtProperties {
    /**
     * Secret key used for signing JWT tokens
     */
    public var secret: String = "5JzoMbk6E5qIqHSuBTgeQCARtUsxAkBiHwdjXOSW8kWdXzYmP3X51C0"
    
    /**
     * Access token expiration time
     */
    public var accessTokenExpiration: Duration = Duration.ofDays(1)
    
    /**
     * Refresh token expiration time
     */
    public var refreshTokenExpiration: Duration = Duration.ofDays(7)
    
    /**
     * Issuer claim for the JWT token
     */
    public var issuer: String = "stahl-users-service"
}

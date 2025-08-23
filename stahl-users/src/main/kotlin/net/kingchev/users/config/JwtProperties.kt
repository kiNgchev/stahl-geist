/*
 *  Stahl geist
 *  Copyright (C) 2025 kiNgchev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

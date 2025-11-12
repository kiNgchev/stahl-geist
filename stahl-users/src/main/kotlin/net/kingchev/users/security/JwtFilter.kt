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

package net.kingchev.users.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.kingchev.shared.catch
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
public class JwtFilter(private val provider: JwtProvider, private val userDetailService: UserDetailService) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        {
            val authorizationHeader = request.getHeader("Authorization")
            var jwt: String? = null
            var username: String? = null

            if (authorizationHeader !== null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7)
                username = provider.extractUsername(jwt)
            }

            if (username !== null && jwt !== null && SecurityContextHolder.getContext().authentication == null) {
                val details = userDetailService.loadUserByUsername(username)

                if (provider.validateToken(jwt, details)) {
                    val token =
                        UsernamePasswordAuthenticationToken(details, null, details.authorities)
                    token.details = details
                    SecurityContextHolder.getContext().authentication = token
                }
            } else {
                throw BadCredentialsException("Bearer token not set correctly")
            }
        }.catch(JwtException::class, BadCredentialsException::class, UnsupportedJwtException::class, MalformedJwtException::class) {
            e -> request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response)
    }
}
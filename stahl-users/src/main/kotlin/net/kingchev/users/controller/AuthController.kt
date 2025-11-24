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

package net.kingchev.users.controller

import jakarta.validation.Valid
import net.kingchev.shared.error.DomainError
import net.kingchev.shared.utils.Result
import net.kingchev.shared.utils.badRequest
import net.kingchev.shared.utils.response
import net.kingchev.users.dto.*
import net.kingchev.users.security.JwtProvider
import net.kingchev.users.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
public class AuthController(private val service: UserService, private val provider: JwtProvider) {
    @PostMapping("/register")
    public fun register(@Valid @RequestBody request: RegistrationRequest): Result<RegisterResponse, DomainError> {
        if (service.existsByUsername(request.username) or service.existsByEmail(request.email))
            return badRequest("User already registered")

        val (name, username, email, password) = request
        val dto = service.create(name, username, email, password)
        val response = RegisterResponse(
            generateToken(dto),
            dto,
            "User successfully registered"
        )

        return response(response)
    }

    @GetMapping("/login")
    public fun login(@Valid @RequestBody request: LoginRequest): Result<LoginResponse, DomainError> {
        if (!service.existsByUsername(request.username))
            return badRequest("User does not exists")

        if (!service.check(request.username, request.password))
            return badRequest("User password invalid")

        val dto = service.findByUsername(request.username)
        val response = LoginResponse(
            generateToken(dto),
            "User successfully logged in"
        )

        return response(response)
    }

    @GetMapping("/check")
    public fun check(@RequestHeader("Authorization") header: String): Result<CheckResponse, DomainError> {

        if (!header.startsWith("Bearer "))
            return badRequest("Bearer is not allowed")

        val jwt: String = header.substring(7)
        val username = provider.extractUsername(jwt)

        if (service.existsByUsername(username)) {
            response(CheckResponse(true))
        }

        return response(CheckResponse(false))
    }

    private fun generateToken(user: UserDto): String {
        return provider.createToken(
            claims = mapOf(
                "email" to user.email,
                "name" to user.name
            ),
            subject = user.username
        )
    }
}
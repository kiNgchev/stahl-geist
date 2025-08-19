package net.kingchev.users.controller

import jakarta.validation.Valid
import net.kingchev.shared.utils.badRequest
import net.kingchev.shared.utils.response
import net.kingchev.users.dto.*
import net.kingchev.users.security.JwtProvider
import net.kingchev.users.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
public class AuthController(
    private val service: UserService,
    private val provider: JwtProvider,
) {
    @PostMapping("/register")
    public fun register(@Valid @RequestBody request: RegistrationRequest): ResponseEntity<RegisterResponse> {
        if (service.existsByUsername(request.username) or service.existsByEmail(request.email)) {
            return badRequest(RegisterResponse(null, null, "User already registered"))
        }
        val (name, username, email, password) = request
        val dto = service.create(name, username, email, password)

        val response = RegisterResponse(generateToken(dto), dto, "User successfully registered")

        return response(response)
    }

    @GetMapping("/login")
    public fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        if (!service.existsByUsername(request.username)) {
            return badRequest(LoginResponse(null, "User does not exists"))
        }

        if (!service.check(request.username, request.password)) {
            return badRequest(LoginResponse(null, "User password invalid"))
        }

        val dto = service.findByUsername(request.username)
        val response = LoginResponse(generateToken(dto), "User successfully logged in")

        return response(response)
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
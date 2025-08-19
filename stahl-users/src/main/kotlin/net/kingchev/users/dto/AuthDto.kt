package net.kingchev.users.dto

import java.io.Serializable

public data class RegistrationRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String
) : Serializable

public data class RegisterResponse(val token: String?, val user: UserDto?, val message: String? = null)

public data class LoginRequest(
    val username: String,
    val password: String
)

public data class LoginResponse(val token: String?, val message: String? = null) : Serializable

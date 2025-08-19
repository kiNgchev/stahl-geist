package net.kingchev.users.dto

import java.io.Serializable

public data class UserDto(
    val id: Long,
    val username: String,
    val email: String? = null,
    val name: String,
    val authorities: MutableSet<AuthorityDto> = mutableSetOf(),
    val avatarUrl: String? = null
) : Serializable

public data class AuthorityDto(val id: Long, val authority: String) : Serializable

public data class UserResponse(val user: UserDto?, val message: String? = null)

public data class UserUpdateRequest(
    val username: String,
    val email: String,
    val password: String,
    val name: String,
    val authorities: MutableSet<AuthorityDto> = mutableSetOf(),
    val avatarUrl: String? = null
)
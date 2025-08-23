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
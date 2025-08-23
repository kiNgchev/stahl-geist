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

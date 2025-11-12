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
import net.kingchev.model.User
import net.kingchev.shared.error.DomainError
import net.kingchev.shared.utils.Result
import net.kingchev.shared.utils.notFound
import net.kingchev.shared.utils.response
import net.kingchev.users.dto.UserDto
import net.kingchev.users.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/@me")
public class SelfController(private val service: UserService) {
    @GetMapping
    public fun me(@AuthenticationPrincipal user: User): Result<UserDto, DomainError> {
        if (user.username == "") {
            return notFound("User is not found")
        }
        return response(service.findByUsername(user.username))
    }

    @PutMapping
    public fun updateMe(@AuthenticationPrincipal user: User, @Valid @RequestBody update: UserDto): Result<UserDto, DomainError> {
        if (user.username == "") {
            return notFound("User not found")
        }

        return response(service.update(update))
    }

    @PatchMapping
    public fun partialUpdateMe(@AuthenticationPrincipal user: User, @Valid @RequestBody update: UserDto): Result<UserDto, DomainError> {
        if (user.username == "") {
            return notFound("User not found")
        }

        return response(service.update(update))
    }

    @DeleteMapping
    public fun deleteMe(@AuthenticationPrincipal user: User): Result<Unit, DomainError> {
        if (user.username == "") {
            return notFound("User not found")
        }

        return response(service.delete(user.username))
    }
}
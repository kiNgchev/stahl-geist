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
import net.kingchev.users.dto.UserDto
import net.kingchev.users.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
public class SelfController(private val service: UserService) {
    @GetMapping("/@me")
    public fun me(@AuthenticationPrincipal user: User): ResponseEntity<UserDto> {
        if (user.username == "") {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(service.findByUsername(user.username))
    }

    @PutMapping("/@me")
    public fun updateMe(@AuthenticationPrincipal user: User, @Valid @RequestBody update: UserDto): ResponseEntity<UserDto> {
        if (user.username == "") {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok().body(service.update(update))
    }

    @PatchMapping("/@me")
    public fun partialUpdateMe(@AuthenticationPrincipal user: User, @Valid @RequestBody update: UserDto): ResponseEntity<UserDto> {
        if (user.username == "") {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok().body(service.update(update))
    }

    @DeleteMapping("/@me")
    public fun deleteMe(@AuthenticationPrincipal user: User): ResponseEntity<Unit> {
        if (user.username == "") {
            return ResponseEntity.notFound().build()
        }
        service.delete(user.username)

        return ResponseEntity.ok().build()
    }
}
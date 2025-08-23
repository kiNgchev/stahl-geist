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

import net.kingchev.shared.utils.badRequest
import net.kingchev.shared.utils.response
import net.kingchev.users.dto.RegistrationRequest
import net.kingchev.users.dto.UserDto
import net.kingchev.users.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/manage")
public class ManagerController(private val service: UserService) {
    @GetMapping("/")
    public fun getAllUsers(): ResponseEntity<List<UserDto>> {
        return response(service.findAll())
    }

    @GetMapping("/{userId}")
    public fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDto> {
        return response(service.findById(userId))
    }

    @PostMapping("/")
    public fun createUser(@RequestBody request: RegistrationRequest): ResponseEntity<UserDto> {
        if (service.existsByUsername(request.username) or service.existsByEmail(request.email)) {
            return badRequest()
        }
        val (name, username, email, password) = request
        val dto = service.create(name, username, email, password)

        return response(dto)
    }

    @DeleteMapping("/{userId}")
    public fun deleteUserById(@PathVariable userId: Long) {
        service.delete(userId)
    }
}

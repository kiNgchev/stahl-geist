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

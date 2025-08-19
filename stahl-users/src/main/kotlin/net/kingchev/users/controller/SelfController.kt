package net.kingchev.users.controller

import jakarta.validation.Valid
import net.kingchev.model.User
import net.kingchev.users.dto.UserDto
import net.kingchev.users.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
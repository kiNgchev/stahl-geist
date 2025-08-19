package net.kingchev.cdn.controller

import net.kingchev.cdn.service.ResourceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
public class ResourceController(private val service: ResourceService) {
    @GetMapping("/avatar/{hash}")
    public fun getAvatar(@PathVariable hash: String): ResponseEntity<ByteArray> {
        TODO("MAKE A AVATAR RESOURCE ACCESS")
    }
}
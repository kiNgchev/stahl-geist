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

package net.kingchev.cdn.controller

import net.kingchev.cdn.service.ResourceService
import net.kingchev.entity.resource.AvatarEntity
import net.kingchev.shared.error.DomainError
import net.kingchev.shared.utils.Result
import net.kingchev.shared.utils.response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
public class ResourceController(private val service: ResourceService) {
    @GetMapping("/avatar/{hash}")
    public fun getAvatar(@PathVariable hash: String): Result<File, DomainError> {
        TODO("MAKE A AVATAR RESOURCE ACCESS")
    }

    @PostMapping("/avatar/upload")
    public fun postAvatar(@RequestBody requestBody: MultipartFile): Result<ByteArray, DomainError> {
        //val inputStream = requestBody.inputStream
        //val avatar = AvatarEntity(name = requestBody.originalFilename, content = inputStream.readBytes())
        //service.saveAvatar(avatar)
        //return response(requestBody.inputStream.readBytes())
        TODO("MAKE A AVATAR RESOURCE ACCESS")
    }
}
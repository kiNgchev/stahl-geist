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

package net.kingchev.cdn.service

import jakarta.persistence.EntityNotFoundException
import net.kingchev.cdn.repository.AvatarRepository
import net.kingchev.entity.resource.AvatarEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Blob
import kotlin.jvm.optionals.getOrElse

@Service
public class ResourceService(private val avatarRepository: AvatarRepository) {
    @Transactional(readOnly = true)
    public fun getAvatar(id: String): AvatarEntity =
        avatarRepository
            .findById(id)
            .getOrElse { throw EntityNotFoundException("Avatar $id Not Found") }

    @Transactional
    public fun saveAvatar(entity: AvatarEntity): AvatarEntity =
        avatarRepository.save(entity)

    @Transactional
    public fun deleteAvatar(id: String): Unit =
        avatarRepository.deleteById(id)

    @Transactional
    public fun deleteAvatar(entity: AvatarEntity): Unit =
        avatarRepository.delete(entity)

    @Transactional
    public fun createAvatar(name: String, content: Blob): AvatarEntity {
        val avatar = AvatarEntity()
        avatar.name = name
        avatar.content = content
        return avatarRepository.save(avatar)
    }
}
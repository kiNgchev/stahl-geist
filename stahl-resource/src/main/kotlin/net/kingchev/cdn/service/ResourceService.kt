package net.kingchev.cdn.service

import jakarta.persistence.EntityNotFoundException
import net.kingchev.cdn.repository.AvatarRepository
import net.kingchev.entity.cdn.AvatarEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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
    public fun createAvatar(name: String, content: ByteArray): AvatarEntity {
        val avatar = AvatarEntity()
        avatar.name = name
        avatar.content = content
        return avatarRepository.save(avatar)
    }
}
package net.kingchev.users.service

import jakarta.persistence.EntityNotFoundException
import net.kingchev.entity.UserEntity
import net.kingchev.model.Role
import net.kingchev.shared.exceptions.EntityAlreadyExistsException
import net.kingchev.users.dto.UserDto
import net.kingchev.users.repository.AuthorityRepository
import net.kingchev.users.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.collections.plus
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Service
public class UserService(
    private val repository: UserRepository,
    private val authorityRepository: AuthorityRepository,
    private val mapper: UserMapper,
    private val encoder: PasswordEncoder
) {
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["user"], key = "#result.username")
    public fun findById(id: Long): UserDto =
        repository.findById(id)
            .map { entity -> mapper.toDto(entity) }
            .getOrElse { throw EntityNotFoundException("User with $id id not found") }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["user"], key = "#username")
    public fun findByUsername(username: String): UserDto =
        repository.findByUsername(username)
            .map { entity -> mapper.toDto(entity) }
            .getOrElse { throw EntityNotFoundException("User with $username username not found") }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["user"], key = "#result.username")
    public fun findByEmail(email: String): UserDto =
        repository.findByEmail(email)
            .map { entity -> mapper.toDto(entity) }
            .getOrElse { throw EntityNotFoundException("User with $email username not found") }

    @Transactional(readOnly = true)
    public fun findAll(): List<UserDto> =
        mapper.toDto(repository.findAll())

    @Transactional
    @CachePut(cacheNames = ["user"], key = "#user.username")
    public fun update(user: UserDto, password: String? = null): UserDto {
        val entity = mapper.toEntity(user)

        if (password != null) {
            entity.password = encoder.encode(password)
        }

        return mapper.toDto(repository.save(entity))
    }

    @Transactional
    @CacheEvict(cacheNames = ["user"], key = "#username")
    public fun delete(username: String): Unit =
        repository.deleteByUsername(username)

    @Transactional
    @CacheEvict(cacheNames = ["user"], key = "#result.username")
    public fun delete(id: Long): Unit =
        repository.deleteById(id)

    @Transactional(readOnly = true)
    public fun existsByUsername(username: String): Boolean =
        repository.existsByUsername(username)

    @Transactional(readOnly = true)
    public fun existsByEmail(email: String): Boolean =
        repository.existsByUsername(email)

    @Transactional
    @CachePut(cacheNames = ["user"], key = "#username")
    public fun create(name: String, username: String, email: String, password: String, vararg authorities: Role = arrayOf()): UserDto {
        if (existsByUsername(username) || existsByEmail(email))
            throw EntityAlreadyExistsException("User with $username already exists")

        val user = UserEntity(name = name, username = username, email = email, password = encoder.encode(password))
        if (authorities.isNotEmpty()) {
            val authorities = authorityRepository.findByAuthorityIn(authorities.toList())
            user.authorities += authorities
        }

        return mapper.toDto(repository.save(user))
    }

    @Transactional
    @CachePut(cacheNames = ["user"], key = "#username")
    public fun updateAuthorities(username: String, vararg authorities: Role): UserDto {
        val entity = repository.findByUsername(username)
            .getOrElse { throw EntityNotFoundException("User with $username username not found") }
        val authorities = authorityRepository.findByAuthorityIn(authorities.toList())
        entity.authorities += authorities

        return mapper.toDto(repository.save(entity))
    }

    @Transactional(readOnly = true)
    public fun check(constraint: String, password: String): Boolean {
        val entity = repository.findByUsername(constraint).getOrNull()
            ?: repository.findByEmail(constraint)
                .getOrElse { throw EntityNotFoundException("User with $constraint not found") }

        return encoder.matches(password, entity.password)
    }
}
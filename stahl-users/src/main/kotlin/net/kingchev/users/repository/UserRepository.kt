package net.kingchev.users.repository

import net.kingchev.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
public interface UserRepository : JpaRepository<UserEntity, Long> {
    public fun findByUsername(username: String): Optional<UserEntity>
    public fun findByEmail(email: String): Optional<UserEntity>

    public fun deleteByUsername(username: String): Unit

    public fun existsByUsername(username: String): Boolean
    public fun existsByEmail(email: String): Boolean
}
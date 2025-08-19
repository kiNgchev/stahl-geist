package net.kingchev.users.repository

import net.kingchev.entity.Authority
import net.kingchev.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
public interface AuthorityRepository : JpaRepository<Authority, Long> {
    public fun findByAuthority(role: Role): Authority

    public fun findByAuthorityIn(authorities: Collection<Role>): List<Authority>
}
package net.kingchev.users.security

import net.kingchev.model.User
import net.kingchev.users.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrElse

@Service
public class UserDetailService(private val repository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails =
        repository.findByUsername(username)
            .map { user ->
                User(
                    username = user.username,
                    password = user.password,
                    authorities = user.authorities.stream()
                        .map { SimpleGrantedAuthority(it.authority.name) }
                        .collect(Collectors.toList())
                )
            }
            .getOrElse { throw UsernameNotFoundException("$username not found") }
}
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
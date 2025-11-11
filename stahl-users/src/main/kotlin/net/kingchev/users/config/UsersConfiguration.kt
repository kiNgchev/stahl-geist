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

package net.kingchev.users.config

import net.kingchev.entity.Authority
import net.kingchev.model.Role
import net.kingchev.users.repository.AuthorityRepository
import net.kingchev.users.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["net.kingchev"])
@EnableJpaRepositories(basePackages = ["net.kingchev.users.repository"])
@ComponentScan(basePackages = ["net.kingchev.users"])
@Import(SecurityConfiguration::class)
public class UsersConfiguration(private val repository: AuthorityRepository) {
    @Bean
    public fun registerAuthorities(): CommandLineRunner {
        return CommandLineRunner {
            Role.entries.forEach {
                repository.save(Authority(it))
            }
        }
    }
}
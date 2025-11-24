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

package net.kingchev.users.repository

import net.kingchev.entity.users.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
public interface UserRepository : JpaRepository<UserEntity, Long> {
    public fun findByUsername(username: String): Optional<UserEntity>
    public fun findByEmail(email: String): Optional<UserEntity>

    public fun deleteByUsername(username: String)

    public fun existsByUsername(username: String): Boolean
    public fun existsByEmail(email: String): Boolean
}
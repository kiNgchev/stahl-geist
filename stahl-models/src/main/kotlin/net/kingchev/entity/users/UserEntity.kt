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

package net.kingchev.entity.users

import jakarta.persistence.*
import net.kingchev.entity.BaseEntity
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "user", schema = "user_management", indexes = [
    Index(name = "idx_user_username_unq", columnList = "username", unique = true),
    Index(name = "idx_user_email_unq", columnList = "email", unique = true),
],  uniqueConstraints = [
    UniqueConstraint(columnNames = ["email"]),
    UniqueConstraint(columnNames = ["username"])
])
public class UserEntity(
    @Column(nullable = false, unique = true)
    public var username: String,

    @Column(nullable = false)
    public var email: String,

    @Column(nullable = false)
    public var name: String,

    @Column(nullable = false)
    public var password: String,

    @ManyToMany
    @JoinTable(name = "user_authority", schema = "user_management",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")])
    public var authorities: MutableSet<Authority> = mutableSetOf(),

    @Column(nullable = true)
    public var avatarUrl: String? = null,
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as UserEntity

        return id == other.id
    }

    override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(avatarUrl = $avatarUrl)"
    }
}
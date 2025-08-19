package net.kingchev.entity

import jakarta.persistence.*
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
    public var authorities: Set<Authority> = setOf(),

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
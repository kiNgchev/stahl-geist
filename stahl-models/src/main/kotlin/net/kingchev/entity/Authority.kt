package net.kingchev.entity

import jakarta.persistence.*
import net.kingchev.model.Role
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "authority", schema = "user_management")
public class Authority(
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    public var authority: Role,
) : BaseEntity() {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Authority

        return id == other.id
    }

    override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}
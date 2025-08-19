package net.kingchev.entity.cdn

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import net.kingchev.entity.BaseEntity
import org.hibernate.proxy.HibernateProxy
import java.time.Instant
import java.util.*

@Entity
@Table(name = "avatar", schema = "resource_management")
public class AvatarEntity(
    @Column
    public var name: String? = null,

    @Column
    public var content: ByteArray? = null,

    @Column
    public val uploadTime: Date = Date.from(Instant.now()),
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as AvatarEntity

        return id == other.id
    }

    override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}
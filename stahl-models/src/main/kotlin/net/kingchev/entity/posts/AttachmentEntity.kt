/*
 * Stahl geist
 * Copyright (C) 2025 kiNgchev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.kingchev.entity.posts

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import net.kingchev.entity.BaseEntity
import org.hibernate.proxy.HibernateProxy
import java.sql.Blob

@Entity
@Table(name = "attachment", schema = "post_management")
public class AttachmentEntity : BaseEntity() {
    @Lob
    @Column(name = "content")
    public var content: Blob? = null

    //TODO: Think about replace this...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    public var comment: CommentEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    public var post: PostEntity? = null

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as AttachmentEntity

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
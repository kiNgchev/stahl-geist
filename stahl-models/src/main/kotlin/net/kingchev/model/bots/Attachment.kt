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

package net.kingchev.model.bots

import java.io.Serializable

public enum class ContentType {
    UNDEFINED,
    IMAGE,
    VIDEO,
    AUDIO,
    VOICE,
    DOCUMENT;

    override fun toString(): String = this.name.lowercase()
}

public data class Attachment(
    var fileName: String,
    var contentType: ContentType,
    var bytes: ByteArray?,
    var attributes: HashMap<String, Any> = hashMapOf()
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Attachment

        if (fileName != other.fileName) return false
        if (contentType != other.contentType) return false
        if (bytes != null) {
            if (other.bytes == null) return false
            if (!bytes.contentEquals(other.bytes)) return false
        } else if (other.bytes != null) return false
        if (attributes != other.attributes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + (bytes?.contentHashCode() ?: 0)
        result = 31 * result + attributes.hashCode()
        return result
    }
}

public fun emptyAttachment(): Attachment = Attachment("null", ContentType.UNDEFINED, null)

public fun Attachment.isEmpty(): Boolean = bytes == null && contentType == ContentType.UNDEFINED

public fun Attachment.isVoice(): Boolean = contentType == ContentType.VOICE
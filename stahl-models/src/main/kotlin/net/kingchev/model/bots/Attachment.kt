package net.kingchev.model.bots

import java.io.Serial
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
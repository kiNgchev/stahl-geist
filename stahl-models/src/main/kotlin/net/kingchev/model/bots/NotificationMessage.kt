package net.kingchev.model.bots

import java.io.Serializable

public enum class NotificationType {
    LIVE_NOTIFICATION
}

public data class NotificationMessage(
    var id: String,
    var source: String,
    var title: String,
    var text: String,
    var url: String? = null,
    var type: NotificationType
) : Serializable

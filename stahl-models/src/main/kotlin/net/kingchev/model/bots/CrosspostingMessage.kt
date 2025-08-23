package net.kingchev.model.bots

import java.io.Serializable

public data class CrosspostingMessage(
    var id: String,
    var source: String,
    var title: String,
    var content: String = "",
    var attachments: List<Attachment>,
) : Serializable
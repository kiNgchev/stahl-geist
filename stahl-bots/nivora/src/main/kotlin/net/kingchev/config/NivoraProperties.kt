package net.kingchev.config

import dev.kord.common.Color
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "nivora")
public data class NivoraProperties(
    public val token: String
)

public object BotMetadata {
    // TODO("ADD STRING")
    public const val ID_STRING: String   = ""
    // TODO("ADD LONG")
    public const val ID_LONG: Long       = 0
    // TODO("ADD USERNAME")
    public const val BOT_USERNAME: String = ""
}


public object Colors {
    public val Purple: Color = Color(0x7567ff)
    public val Critical: Color = Color(0xff0900)
    public val Red: Color = Color(0xDC143C)
}

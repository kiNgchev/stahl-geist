package net.kingchev.command.model

import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public interface NivoraCommand {
    public suspend fun validate(event: GuildChatInputCommandInteractionCreateEvent): Boolean

    public suspend fun execute(event: GuildChatInputCommandInteractionCreateEvent)

    public companion object {
        public val logger: Logger = LoggerFactory.getLogger(NivoraCommand::class.java)
    }
}
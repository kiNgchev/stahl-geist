package net.kingchev.events.impl

import dev.kord.common.entity.optional.orEmpty
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import net.kingchev.NivoraClient
import net.kingchev.config.NivoraHolder
import net.kingchev.events.model.NivoraListener
import org.springframework.stereotype.Component

@Component
public class CommandHandler(client: NivoraClient, holder: NivoraHolder) : NivoraListener {
    init {
        client.on<GuildChatInputCommandInteractionCreateEvent> {
            val invokedCommandName = interaction.invokedCommandName
            val subCommand = interaction.command.data.options.orEmpty().firstOrNull()?.name

            val command = holder.commands()[invokedCommandName]
                ?: holder.commands()[subCommand]
                ?: holder.groups()[invokedCommandName]?.commands[subCommand]
                ?: return@on

            if (command.validate(this))
                command.execute(this)
        }
    }
}
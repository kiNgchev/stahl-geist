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
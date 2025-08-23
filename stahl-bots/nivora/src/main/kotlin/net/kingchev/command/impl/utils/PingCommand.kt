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

package net.kingchev.command.impl.utils

import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.rest.builder.message.EmbedBuilder
import net.kingchev.NivoraClient
import net.kingchev.command.model.AbstractCommand
import net.kingchev.config.BotMetadata
import net.kingchev.config.Colors
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicLong
import kotlin.time.DurationUnit

@Component
public class PingCommand(private val client: NivoraClient)
    : AbstractCommand({ key("ping"); description("") }) {

    override fun build(): GlobalChatInputCreateBuilder.() -> Unit = {
        this.apply(super.build())
    }

    override suspend fun validate(event: GuildChatInputCommandInteractionCreateEvent): Boolean = true

    override suspend fun execute(event: GuildChatInputCommandInteractionCreateEvent) {
        val restPing = getRestPing(event.kord)
        val interaction = event.interaction.deferPublicResponse()
        val embed = EmbedBuilder()

        embed.field {
            name = "REST"
            value = "```${restPing}ms```"
            inline = true
        }
        embed.field {
            name = "WS"
            value = "```${client.gateway.averagePing?.toInt(DurationUnit.MILLISECONDS).toString()}ms```"
            inline = true
        }
        embed.color = Colors.Red

        interaction.respond {
            embeds = mutableListOf(embed)
        }
    }

    private suspend fun getRestPing(client: NivoraClient): Long {
        val time = AtomicLong(System.currentTimeMillis())
        client.rest.user.getUser(Snowflake(BotMetadata.ID_STRING))
        return System.currentTimeMillis() - time.get()
    }
}
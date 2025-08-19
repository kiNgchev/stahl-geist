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
package net.kingchev.command.model

import dev.kord.rest.builder.interaction.SubCommandBuilder
import net.kingchev.command.model.builder.CommandBuilder
import net.kingchev.command.model.data.CommandData
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public abstract class AbstractSubCommand(builder: CommandBuilder.() -> Unit) : NivoraCommand {
    public val data: CommandData = CommandBuilder().apply(builder).build()

    public open fun build(): SubCommandBuilder.() -> Unit = {
        description = data.description
    }

    protected companion object {
        public val logger: Logger = LoggerFactory.getLogger(AbstractSubCommand::class.java)
    }
}
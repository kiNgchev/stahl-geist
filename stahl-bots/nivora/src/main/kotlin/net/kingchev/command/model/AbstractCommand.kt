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

package net.kingchev.command.model

import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import net.kingchev.command.model.builder.CommandBuilder
import net.kingchev.command.model.data.CommandData
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public abstract class AbstractCommand(builder: CommandBuilder.() -> Unit) : NivoraCommand {
    public val data: CommandData = CommandBuilder().apply(builder).build()

    public open fun build(): GlobalChatInputCreateBuilder.() -> Unit = {
        description = data.description
    }

    protected companion object {
        public val logger: Logger = LoggerFactory.getLogger(AbstractCommand::class.java)
    }
}
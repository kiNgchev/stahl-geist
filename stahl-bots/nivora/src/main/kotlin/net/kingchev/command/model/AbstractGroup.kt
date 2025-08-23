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
import net.kingchev.command.model.builder.GroupBuilder
import net.kingchev.command.model.data.GroupData
import org.slf4j.LoggerFactory

public abstract class AbstractGroup(builder: GroupBuilder.() -> Unit) {
    public val data: GroupData = GroupBuilder().apply(builder).build()
    public val commands: HashMap<String, NivoraCommand> = hashMapOf()

    public open fun build(): GlobalChatInputCreateBuilder.() -> Unit = {
        description = data.description
    }

    public companion object {
        private val logger = LoggerFactory.getLogger(AbstractGroup::class.java)
    }
}
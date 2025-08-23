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

package net.kingchev.config

import kotlinx.coroutines.runBlocking
import net.kingchev.NivoraClient
import net.kingchev.command.model.AbstractCommand
import net.kingchev.command.model.AbstractGroup
import net.kingchev.command.model.NivoraCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
public class NivoraHolder(private val client: NivoraClient) {
    private val commands: MutableMap<String, NivoraCommand> = mutableMapOf()
    private var groups: MutableMap<String, AbstractGroup> = mutableMapOf()

    @Autowired(required = false)
    private fun init(commands: Array<AbstractCommand>) {
        commands.forEach { instance ->
            try {
                this.commands[instance.data.key] = instance
                logger.info("Command [${instance.data.key}] has been registered")
            } catch (_: IllegalArgumentException) {
                logger.error("Error occurred while command [${instance.data.key}] be registered")
            }
        }

        this.commands.forEach { (key, command) ->
            val command = command as AbstractCommand
            runBlocking {
                client.createGlobalChatInputCommand(key, command.data.description, command.build())
            }
            logger.info("Command [$key] has been registered in discord app")
        }

        clearCommands(client)
    }

    @Autowired(required = false)
    private fun init(groups: Array<AbstractGroup>) {
        groups.forEach { instance ->
            try {
                this.groups[instance.data.name] = instance
                logger.info("Command group [${instance.data.name}] has been registered")
            } catch (_: IllegalArgumentException) {
                logger.error("Error occurred while command group [${instance.data.name}] be registered")
            }
        }

        this.groups.forEach { (key, group) ->
            runBlocking {
                client.createGlobalChatInputCommand(key, group.data.description, group.build())
            }
        }
        clearCommands(client)
    }

    public fun commands(): Map<String, NivoraCommand> = this.commands
    public fun groups(): Map<String, AbstractGroup> = this.groups

    public fun clearCommands(client: NivoraClient): Unit = runBlocking {
        client.getGlobalApplicationCommands().collect {
            if (!groups.containsKey(it.name) and !commands.containsKey(it.name)) {
                it.delete()
                logger.info("Command or commands group [${it.name}] has been deleted")
            }
        }
    }

    public companion object {
        public val logger: Logger = LoggerFactory.getLogger(NivoraHolder::class.java)
    }
}
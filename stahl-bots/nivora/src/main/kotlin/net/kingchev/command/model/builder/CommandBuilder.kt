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

package net.kingchev.command.model.builder

import net.kingchev.command.model.data.CommandData

public class CommandBuilder {
    private var key: String = ""
    private var aliases: Array<String> = arrayOf()
    private var description: String = ""
    private var developerOnly: Boolean = false
    private var guildOnly: Boolean = true
    private var dmOnly: Boolean = false


    public fun key(value: String) {
        key = value
    }

    public fun aliases(value: Array<String>) {
        aliases = value
    }

    public fun alias(value: String) {
        aliases += value
    }

    public fun description(value: String) {
        description = value
    }

    public fun developerOnly(value: Boolean) {
        developerOnly = value
    }

    public fun guildOnly(value: Boolean) {
        guildOnly = value
    }

    public fun dmOnly(value: Boolean) {
        dmOnly = value
    }

    public fun build(): CommandData {
        return CommandData(key, aliases, description, developerOnly, guildOnly, dmOnly)
    }
}
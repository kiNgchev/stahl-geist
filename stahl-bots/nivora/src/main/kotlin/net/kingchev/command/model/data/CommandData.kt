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

package net.kingchev.command.model.data

public data class CommandData(
    public val key: String,
    public val aliases: Array<String>,
    public val description: String,
    public val developerOnly: Boolean = false,
    public val guildOnly: Boolean = true,
    public val dmOnly: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandData

        if (developerOnly != other.developerOnly) return false
        if (guildOnly != other.guildOnly) return false
        if (dmOnly != other.dmOnly) return false
        if (key != other.key) return false
        if (!aliases.contentEquals(other.aliases)) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + guildOnly.hashCode()
        result = 31 * result + dmOnly.hashCode()
        result = 31 * result + developerOnly.hashCode()
        result = 31 * result + aliases.contentHashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}

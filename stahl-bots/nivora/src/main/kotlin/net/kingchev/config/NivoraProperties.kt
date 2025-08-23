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

import dev.kord.common.Color
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "nivora")
public data class NivoraProperties(
    public val token: String
)

public object BotMetadata {
    // TODO("ADD STRING")
    public const val ID_STRING: String   = ""
    // TODO("ADD LONG")
    public const val ID_LONG: Long       = 0
    // TODO("ADD USERNAME")
    public const val BOT_USERNAME: String = ""
}


public object Colors {
    public val Purple: Color = Color(0x7567ff)
    public val Critical: Color = Color(0xff0900)
    public val Red: Color = Color(0xDC143C)
}

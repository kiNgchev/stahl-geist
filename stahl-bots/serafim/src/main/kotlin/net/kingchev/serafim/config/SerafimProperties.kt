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

package net.kingchev.serafim.config

import org.springframework.boot.context.properties.ConfigurationProperties

private const val PREFIX = "serafim"

@ConfigurationProperties(prefix = PREFIX)
public class SerafimProperties {
    public var tokens: Tokens = Tokens()
    public var twitchChannel: List<String> = mutableListOf()
    public var username: String? = null

    @ConfigurationProperties(prefix = "${PREFIX}.tokens")
    public class Tokens {
        public var clientId: String? = null
        public var clientSecret: String? = null
        public var irc: String? = null
    }
}
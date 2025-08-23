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

import dev.kord.gateway.Intent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.kingchev.Nivora
import net.kingchev.NivoraBotApplication
import net.kingchev.NivoraClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(NivoraProperties::class)
public class NivoraConfiguration(private val props: NivoraProperties) {
    @Bean
    public fun nivoraClient(): NivoraClient {
        val client = Nivora(props.token)

        GlobalScope.launch {
            client.login() {
                Intent.entries.forEach { intents += it }
                logger.info("Bot was started")
            }
        }

        return client
    }

    public companion object {
        public val logger: Logger = LoggerFactory.getLogger(NivoraBotApplication::class.java)
    }
}
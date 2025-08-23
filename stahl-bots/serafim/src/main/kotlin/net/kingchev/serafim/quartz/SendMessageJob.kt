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

package net.kingchev.serafim.quartz

import com.github.twitch4j.ITwitchClient
import net.kingchev.serafim.config.SerafimProperties
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
public class SendMessageJob(private val twitchClient: ITwitchClient, private val props: SerafimProperties) {
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    private fun sendMessage() {
        props.twitchChannel.forEach {
            twitchClient.chat.sendMessage(
                it,
                "Не забывайте подписываться на наш телеграм канал! https://t.me/k1ngchev"
            )
        }
    }
}
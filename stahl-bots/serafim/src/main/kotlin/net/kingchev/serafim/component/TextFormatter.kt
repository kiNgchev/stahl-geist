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

package net.kingchev.serafim.component

import com.github.twitch4j.events.ChannelGoLiveEvent

public object TextFormatter {
    private val templates = listOf(
        "{channel.name} Запустил трансляцию {stream.title}\nНе пропусти! {stream.url}"
    )
    private val values = { event: ChannelGoLiveEvent -> mapOf(
        "channel.name" to event.channel.name,
        "channel.id" to event.channel.id,
        "stream.title" to event.stream.title,
        "stream.url" to "https://www.twitch.tv/${event.channel.name}"
    ) }

    public fun format(event: ChannelGoLiveEvent): String {
        val template = templates.random()
        return format(template, values(event))
    }

    private fun format(template: String, map: Map<String, String>): String =
        map.entries.fold(template) { acc, entry ->
            acc.replace("{${entry.key}}", entry.value)
        }
}
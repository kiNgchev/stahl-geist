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

package net.kingchev.shared.utils

public class MapBuilder<K, V> {
    private val map = mutableMapOf<K, V>()

    public infix fun K.to(value: V): V? = map.put(this, value)

    public fun build(): Map<K, V> = map.toMap()
}

public fun <K, V> buildMap(block: MapBuilder<K, V>.() -> Unit): MutableMap<K, V> {
    val builder = MapBuilder<K, V>().apply(block)
    val result = mutableMapOf<K, V>()
    result.putAll(builder.build())

    return result
}

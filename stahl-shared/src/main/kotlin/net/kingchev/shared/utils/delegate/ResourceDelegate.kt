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

@file:Suppress("UNCHECKED_CAST")

package net.kingchev.shared.utils.delegate

import java.io.FileNotFoundException
import java.util.*
import kotlin.reflect.KProperty

public class Property<T>(
    private val key: String,
    private val default: T?,
    private var path: String = "application.properties",
    system: Boolean = false,
    private val block: (String) -> T
) {
    private val properties: Properties

    init {
        if (!system) {
            if (!path.startsWith("/"))
                path = "/$path"
            properties = Properties()
            properties.load(javaClass.getResourceAsStream(path)
                ?: throw FileNotFoundException("Properties file not found on path: $path")
            )
        } else properties = System.getProperties()
    }

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val result = properties.getProperty(key)
            ?: return default
            ?: throw NullPointerException("This property value is not defined for $key")

        return block(result)
    }

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
        = throw UnsupportedOperationException("You may not change this value")
}

public class Environment<T>(
    private val key: String,
    private val default: T?,
    private val block: (String) -> T
) {
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val result = System.getenv(key)
            ?: return default
            ?: throw NullPointerException("This environment value is not defined for $key")

        return block(result)
    }

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
        = throw UnsupportedOperationException("You may not change this value")
}

public fun <T> property(
    key: String,
    path: String,
    system: Boolean = false,
    default: T? = null,
    block: (String) -> T = { it as T }
): Property<T> =
    Property(key, default, path, system, block)

public fun <T> property(
    key: String,
    default: T? = null,
    system: Boolean = false,
    block: (String) -> T = { it as T }
): Property<T> =
    Property(key, default, system = system, block = block)

public fun <T> env(key: String, default: T? = null, block: (String) -> T = { it as T }): Environment<T> =
    Environment(key, default, block)
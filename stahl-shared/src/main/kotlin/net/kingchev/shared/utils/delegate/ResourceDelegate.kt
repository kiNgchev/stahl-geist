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

        if (result == null && default == null)
            throw NullPointerException("This property value is not defined for $key")
        else if (result == null && default != null)
            return default

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

        if (result == null && default == null)
            throw NullPointerException("This environment value is not defined for $key")
        else if (result == null && default != null)
            return default

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
package net.kingchev.shared

import kotlin.reflect.KClass

public fun <R> (() -> R).catch(vararg exceptions: KClass<out Throwable>, handler: (ex: Exception) -> R): R =
    try {
        this()
    } catch (ex: Exception) {
        if (ex::class in exceptions)
            handler(ex)
        else throw ex
    }

public suspend fun <R> (suspend () -> R).catch(vararg exceptions: KClass<out Throwable>, handler: (ex: Exception) -> R): R =
    try {
        this()
    } catch (ex: Exception) {
        if (ex::class in exceptions)
            handler(ex)
        else throw ex
    }
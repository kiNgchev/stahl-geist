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
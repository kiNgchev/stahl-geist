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

import java.util.*

private typealias ErrorFunction <E> = () -> E

public sealed interface Result<T, E : Throwable> {
    public companion object {
        public fun <T, E : Throwable> ok(value: T): Result<T, E> = Ok(value)
        public fun <T, E : Throwable> err(error: E): Result<T, E> = Err(error)

        public fun <T, E : Throwable> ofNullable(value: T?, error: ErrorFunction<E>): Result<T, E> =
            ofNullable(value, error())

        public fun <T, E : Throwable> ofNullable(value: T?, error: E): Result<T, E> =
            if (value != null)
                ok(value)
            else
                err(error)

        public fun <T, E : Throwable> fromOptional(optional: Optional<T?>, error: ErrorFunction<E>): Result<T, E> =
            fromOptional(optional, error())

        public fun <T, E : Throwable> fromOptional(optional: Optional<T?>, error: E): Result<T, E> =
            if (optional.isPresent)
                ok(optional.get())
            else
                err(error)

        public inline fun <T, reified E : Throwable> wrap(func: () -> T, error: E): Result<T, E> =
            try {
                ok(func())
            } catch (e: Exception) {
                if (e is E) err(error)
                else throw e
            }

        public inline fun <T, reified E : Throwable> wrap(func: () -> T, error: ErrorFunction<E>): Result<T, E> =
            try {
                ok(func())
            } catch (e: Exception) {
                if (e is E) err(error())
                else throw e
            }

        public suspend inline fun <T, reified E : Throwable> wrap(func: suspend() -> T, error: E): Result<T, E> =
            try {
                ok(func())
            } catch (e: Exception) {
                if (e is E) err(error)
                else throw e
            }
    }

    public fun value(): T
    public fun error(): E

    public fun isOk(): Boolean
    public fun isErr(): Boolean = !isOk()

    public fun <U> map(mapper: (T) -> U): Result<U, E>
    public fun <U> flatMap(mapper: (T) -> Result<U, E>): Result<U, E>

    public data class Ok<T, E : Throwable>(val value: T) : Result<T, E> {
        override fun value(): T = value

        override fun error(): E = throw NoSuchElementException("The error field is not available from Result.Ok")

        override fun isOk(): Boolean = true

        override fun <U> map(mapper: (T) -> U): Result<U, E> = ok(mapper(value))

        override fun <U> flatMap(mapper: (T) -> Result<U, E>): Result<U, E> = mapper(value)
    }

    public data class Err<T, E : Throwable>(val err: E) : Result<T, E> {
        override fun value(): T = throw NoSuchElementException("The value field is not available from Result.Err")

        override fun error(): E = err

        override fun isOk(): Boolean = false

        override fun <U> map(mapper: (T) -> U): Result<U, E> = err(err)

        override fun <U> flatMap(mapper: (T) -> Result<U, E>): Result<U, E> = err(err)
    }
}

public fun <T, E : Throwable> Optional<T?>.result(error:ErrorFunction<E>): Result<T, E> {
    return Result.fromOptional<T, E>(this, error)
}

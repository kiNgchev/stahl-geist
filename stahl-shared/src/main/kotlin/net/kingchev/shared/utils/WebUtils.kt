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

import net.kingchev.shared.dto.ErrorDto
import net.kingchev.shared.error.DomainError
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

public fun error(error: DomainError): ResponseEntity<ErrorDto> {
    val status = error.status
    val body = ErrorDto(
        error.message,
        LocalDateTime.now()
    )
    return response(status, body)
}

public fun <T : Any> response(status: Int, body: T? = null): ResponseEntity<T> =
    response(HttpStatusCode.valueOf(status), body)

public fun <T : Any> response(status: HttpStatusCode, body: T? = null): ResponseEntity<T> =
    ResponseEntity
        .status(status)
        .body(body)

public fun <T> response(body: T): Result<T, DomainError> =
    Result.ok(body)

public fun <T> unauthorized(message: String): Result<T, DomainError> =
    Result.err(DomainError.Unauthorized(message))

public fun <T> notFound(message: String): Result<T, DomainError> =
    Result.err(DomainError.NotFound(message))

public fun <T> badRequest(message: String): Result<T, DomainError> =
    Result.err(DomainError.BadRequest(message))

public fun <T> forbidden(message: String): Result<T, DomainError> =
    Result.err(DomainError.Forbidden(message))

public fun <T> internal(message: String): Result<T, DomainError> =
    Result.err(DomainError.Internal(message))
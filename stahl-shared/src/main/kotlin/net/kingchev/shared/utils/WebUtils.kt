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

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity

public fun <T> response(body: T): ResponseEntity<T> =
    ResponseEntity
        .ok()
        .body(body)

public fun <T> response(status: Int, body: T? = null): ResponseEntity<T> =
    response(HttpStatusCode.valueOf(status), body)

public fun <T> response(status: HttpStatusCode, body: T? = null): ResponseEntity<T> =
    ResponseEntity
        .status(status)
        .body(body)

public fun <T> badRequest(body: T? = null): ResponseEntity<T> =
    ResponseEntity
        .badRequest()
        .body(body)

public fun notFound(): ResponseEntity<*> =
    ResponseEntity
        .notFound()
        .build<Any>()

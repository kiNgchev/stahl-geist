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

package net.kingchev.shared.error

import org.springframework.http.HttpStatus

public interface DomainError {
    public val message: String
    public val status: HttpStatus

    public class Unauthorized(
        override val message: String,
        override val status: HttpStatus = HttpStatus.UNAUTHORIZED
    ) : DomainError

    public class NotFound(
        override val message: String,
        override val status: HttpStatus = HttpStatus.NOT_FOUND
    ) : DomainError

    public class BadRequest(
        override val message: String,
        override val status: HttpStatus = HttpStatus.BAD_REQUEST
    ) : DomainError

    public class Forbidden(
        override val message: String,
        override val status: HttpStatus = HttpStatus.FORBIDDEN
    ) : DomainError

    public class Internal(
        override val message: String,
        override val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    ) : DomainError
}
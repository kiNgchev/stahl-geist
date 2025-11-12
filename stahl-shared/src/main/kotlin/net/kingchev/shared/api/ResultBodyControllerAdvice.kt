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

package net.kingchev.shared.api

import net.kingchev.shared.error.DomainError
import net.kingchev.shared.utils.Result
import net.kingchev.shared.utils.error
import net.kingchev.shared.utils.response
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
public class ResultBodyControllerAdvice : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean =
        Result::class.java.isAssignableFrom(returnType.parameterType)

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        if (body !is Result<*, *>)
            return body

        if (body.isOk())
            return body.value()

        if (body.error() is DomainError) {
            val error = body.error() as DomainError
            response.setStatusCode(error.status)
            return error(error).body
        }

        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
        return mapOf("error" to "Unexpected error:  ${body.error()::class.java.getSimpleName()}")
    }
}
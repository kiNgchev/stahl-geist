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

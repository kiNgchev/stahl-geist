@file:OptIn(ExperimentalContracts::class)

package net.kingchev

import dev.kord.core.Kord
import dev.kord.core.builder.kord.KordBuilder
import kotlinx.coroutines.runBlocking
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public typealias NivoraClient = Kord

@Suppress("FunctionName")
public fun Nivora(token: String, builder: KordBuilder.() -> Unit = {}): NivoraClient {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    return runBlocking { KordBuilder(token).apply(builder).build() }
}

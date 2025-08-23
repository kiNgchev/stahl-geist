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

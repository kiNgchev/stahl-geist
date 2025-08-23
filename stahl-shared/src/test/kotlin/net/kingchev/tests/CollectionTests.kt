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

package net.kingchev.tests

import net.kingchev.shared.utils.buildMap
import kotlin.test.Test
import kotlin.test.assertEquals

class CollectionTests {
    @Test
    fun `test map builder`() {
        val excepted = buildMap {
            1 to "1"
            2 to "3"
        }
        val actual = mapOf(1 to "1", 2 to "3")
        assertEquals(excepted, actual)
    }
}
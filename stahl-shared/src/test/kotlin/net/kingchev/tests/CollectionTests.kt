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
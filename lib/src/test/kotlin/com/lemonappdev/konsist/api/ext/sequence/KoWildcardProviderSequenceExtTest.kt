package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoWildcardProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoWildcardProviderSequenceExtTest {
    @Test
    fun `withWildcard() returns import with wildcard`() {
        // given
        val import1: KoWildcardProvider = mockk {
            every { isWildcard } returns true
        }
        val import2: KoWildcardProvider = mockk {
            every { isWildcard } returns false
        }
        val imports = sequenceOf(import1, import2)

        // when
        val sut = imports.withWildcard()

        // then
        sut.toList() shouldBeEqualTo listOf(import1)
    }

    @Test
    fun `withoutWildcard() returns import without wildcard`() {
        // given
        val import1: KoWildcardProvider = mockk {
            every { isWildcard } returns true
        }
        val import2: KoWildcardProvider = mockk {
            every { isWildcard } returns false
        }
        val imports = sequenceOf(import1, import2)

        // when
        val sut = imports.withoutWildcard()

        // then
        sut.toList() shouldBeEqualTo listOf(import2)
    }
}
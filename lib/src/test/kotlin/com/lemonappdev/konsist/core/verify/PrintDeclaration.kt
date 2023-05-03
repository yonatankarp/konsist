package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.core.ext.print
import com.lemonappdev.konsist.core.ext.withNamePrefix
import com.lemonappdev.konsist.core.scope.KoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class PrintDeclaration {
    @Test
    fun `return value after printDeclaration() is equal to original value`() {
        // given
        val scope = KoScope.fromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val sut = scope
            .classes()
            .withNamePrefix("KoScopeForC")

        // then
        sut.print() shouldBeEqualTo sut
    }
}

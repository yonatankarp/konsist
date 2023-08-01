package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoParentProviderTest {
    @Test
    fun `interface-without-parent`() {
        // given
        val sut = getSnippetFile("interface-without-parent")
            .interfaces()
            .first()

        // then
        sut.parent shouldBeEqualTo null
    }

    @Test
    fun `interface-with-parent`() {
        // given
        val sut = getSnippetFile("interface-with-parent")
            .interfaces(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            parent shouldNotBeEqualTo null
            (parent as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkoparentprovider/", fileName)
}
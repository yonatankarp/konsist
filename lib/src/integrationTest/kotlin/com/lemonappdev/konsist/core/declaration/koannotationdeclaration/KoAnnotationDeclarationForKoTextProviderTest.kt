package com.lemonappdev.konsist.core.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoTextProviderTest {
    @Test
    fun `annotation-text`() {
        // given
        val sut = getSnippetFile("annotation-text")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                    @SampleAnnotationWithParameter(sampleParameter = "text")
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotationdeclaration/snippet/forkotextprovider/", fileName)
}

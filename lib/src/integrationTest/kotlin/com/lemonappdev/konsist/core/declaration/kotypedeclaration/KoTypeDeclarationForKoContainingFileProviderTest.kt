package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoContainingFileProviderTest {
    @Test
    fun `type-containing-file`() {
        // given
        val sut = getSnippetFile("type-containing-file")
            .properties()
            .first()
            .explicitType

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkocontainingfileprovider/", fileName)
}

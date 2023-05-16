package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForImportAliasTest {

    @Test
    fun `import-alias`() {
        // given
        val sut = getSnippetFile("import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.importAliasName shouldBeEqualTo "ImportAlias"
            it?.name shouldBeEqualTo "ImportAlias"
            it?.isImportAlias() shouldBeEqualTo true
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forimportalias/", fileName)
}

package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoParentInterfaceProviderTest {
    @Test
    fun `interface-has-no-parent-interface`() {
        // given
        val sut = getSnippetFile("interface-has-no-parent-interface")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.toList() shouldBeEqualTo emptyList()
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaces("SampleInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-parent-interfaces`() {
        // given
        val sut = getSnippetFile("interface-has-parent-interfaces")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name }.toList() shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkoparentinterfaceprovider/", fileName)
}
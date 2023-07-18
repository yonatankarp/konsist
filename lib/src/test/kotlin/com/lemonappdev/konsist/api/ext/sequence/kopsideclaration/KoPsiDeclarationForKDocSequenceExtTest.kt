package com.lemonappdev.konsist.api.ext.sequence.kopsideclaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.ext.sequence.withKDoc
import com.lemonappdev.konsist.api.ext.sequence.withKDocWithAllTags
import com.lemonappdev.konsist.api.ext.sequence.withKDocWithSomeTags
import com.lemonappdev.konsist.api.ext.sequence.withKDocWithTags
import com.lemonappdev.konsist.api.ext.sequence.withValidKDoc
import com.lemonappdev.konsist.api.ext.sequence.withoutKDoc
import com.lemonappdev.konsist.api.ext.sequence.withoutKDocWithAllTags
import com.lemonappdev.konsist.api.ext.sequence.withoutKDocWithSomeTags
import com.lemonappdev.konsist.api.ext.sequence.withoutKDocWithTags
import com.lemonappdev.konsist.api.ext.sequence.withoutValidKDoc
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForKDocSequenceExtTest {
    @Test
    fun `withKDoc() returns psiDeclaration with any kDoc`() {
        // given
        val psiDeclaration1: KoKDocProvider = mockk {
            every { hasKDoc() } returns true
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { hasKDoc() } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDoc()

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutKDoc() returns psiDeclaration without any kDoc`() {
        // given
        val psiDeclaration1: KoKDocProvider = mockk {
            every { hasKDoc() } returns true
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { hasKDoc() } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDoc()

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withValidDoc() returns psiDeclaration with complete kDoc`() {
        // given
        val psiDeclaration1: KoKDocProvider = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns true
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withValidKDoc(verifyDescription = true, verifyParamTag = true)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutValidDoc() returns psiDeclaration without complete kDoc`() {
        // given
        val psiDeclaration1: KoKDocProvider = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns true
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutValidKDoc(verifyDescription = true, verifyParamTag = true)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withKDocWithTags() returns psiDeclaration with any tag`() {
        // given
        val tag: KoKDocTagDeclaration = mockk()
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { tags } returns listOf(tag)
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { tags } returns emptyList()
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDocWithTags()

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutKDocWithTags() returns psiDeclaration without any tag`() {
        // given
        val tag: KoKDocTagDeclaration = mockk()
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { tags } returns listOf(tag)
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { tags } returns emptyList()
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDocWithTags()

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withKDocWithAllTags(String) returns psiDeclaration with given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns true
        }
        val psiDeclaration1: KoKDocProvider = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns false
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDocWithAllTags(tag)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withKDocWithAllTags(String) returns psiDeclaration with all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns true
        }
        val psiDeclaration1: KoKDocProvider = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns false
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDocWithAllTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutKDocWithAllTags(String) returns psiDeclaration without given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns true
        }
        val psiDeclaration1: KoKDocProvider = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns false
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDocWithAllTags(tag)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withoutKDocWithAllTags(String) returns psiDeclaration without any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns true
        }
        val psiDeclaration1: KoKDocProvider = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns false
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDocWithAllTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withKDocWithSomeTags(String) returns psiDeclarations with given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns true
        }
        val psiDeclaration1: KoKDocProvider = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns false
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDocWithSomeTags(tag)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withKDocWithSomeTags(String) returns psiDeclarations with at least one of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns true
            every { hasTags(tag2) } returns true
        }
        val psiDeclaration1: KoKDocProvider = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns true
            every { hasTags(tag2) } returns false
        }
        val psiDeclaration2: KoKDocProvider = mockk {
            every { kDoc } returns kDoc2
        }
        val kDoc3: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns false
            every { hasTags(tag2) } returns false
        }
        val psiDeclaration3: KoKDocProvider = mockk {
            every { kDoc } returns kDoc3
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withKDocWithSomeTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

    @Test
    fun `withoutKDocWithSomeTags(String) returns psiDeclarations with given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns true
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag) } returns false
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDocWithSomeTags(tag)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withoutKDocWithSomeTags(String) returns psiDeclarations with at least one of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns true
            every { hasTags(tag2) } returns true
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns true
            every { hasTags(tag2) } returns false
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val kDoc3: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns false
            every { hasTags(tag2) } returns false
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc3
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutKDocWithSomeTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }
}

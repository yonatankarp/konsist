package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPropertyTypeProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyTypeProviderListExtTest {
    @Test
    fun `withType() returns declaration with any type`() {
        // given
        val declaration1: KoPropertyTypeProvider = mockk {
            every { hasType() } returns true
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { hasType() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withType(name) returns declarations with one of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoPropertyTypeProvider = mockk {
            every { hasType(typeName1) } returns true
            every { hasType(typeName2) } returns false
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns true
        }
        val declaration3: KoPropertyTypeProvider = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutType() returns declaration without any type`() {
        // given
        val declaration1: KoPropertyTypeProvider = mockk {
            every { hasType() } returns true
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { hasType() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutType(name) returns declaration without any of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoPropertyTypeProvider = mockk {
            every { hasType(typeName1) } returns true
            every { hasType(typeName2) } returns false
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns true
        }
        val declaration3: KoPropertyTypeProvider = mockk {
            every { hasType(typeName1) } returns false
            every { hasType(typeName2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTypeOf(KClass) returns declaration with given return type`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName1
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeOf(KClass) returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName1
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName2
        }
        val declaration3: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns declaration without given return type`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val declaration1: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName1
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName1
        }
        val declaration2: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName2
        }
        val declaration3: KoPropertyTypeProvider = mockk {
            every { type?.name } returns typeName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
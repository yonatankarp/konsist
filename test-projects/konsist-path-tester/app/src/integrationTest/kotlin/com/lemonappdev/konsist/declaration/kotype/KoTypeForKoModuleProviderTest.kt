package com.lemonappdev.konsist.declaration.kotype

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.types
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeForKoModuleProviderTest {
    private val app = "app"
    private val data = "data"
    private val root = "root"

    @Test
    fun `module name is 'app'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .properties()
            .types
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo app
            resideInModule(app) shouldBeEqualTo true
            resideInModule(data) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'data'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator())
            .properties()
            .types
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo data
            resideInModule(data) shouldBeEqualTo true
            resideInModule(app) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'root'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator())
            .properties()
            .types
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo root
            resideInModule(root) shouldBeEqualTo true
            resideInModule(app) shouldBeEqualTo false
        }
    }
}

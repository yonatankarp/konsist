package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.container.KoFileImpl
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationImpl private constructor(
    private val ktTypeReference: KtTypeReference,
) :
    KoBaseDeclarationImpl(ktTypeReference),
    KoTypeDeclaration {
    private val file: KoFile by lazy { KoFileImpl(ktTypeReference.containingKtFile) }

    override val aliasType: String? by lazy {
        file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text.removeSuffix("?") }
            ?.alias
    }

    override val name: String by lazy {
        when {
            isAlias() -> aliasType + if (isNullable) "?" else ""
            else -> ktTypeReference.text
        }
    }

    override val sourceType: String by lazy {
        if (isAlias()) {
            file
                .imports
                .first { it.alias == ktTypeReference.text.removeSuffix("?") }
                .name
                .split(".")
                .toMutableList()
                .last { it.isNotBlank() }
        } else {
            name
                .removeSuffix("?")
        }
    }

    override val isNullable: Boolean by lazy { ktTypeReference.text.last() == '?' }

    override val isGenericType: Boolean by lazy {
        val regex = "\\w+<[^<>]+>".toRegex()

        regex.matches(sourceType)
    }

    override val fullyQualifiedName: String by lazy {
        file
            .imports
            .map { it.name }
            .firstOrNull { it.contains(sourceType) } ?: ""
    }

    override fun isAlias(): Boolean = aliasType != null

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeReference: KtTypeReference, parentDeclaration: KoParentProvider?): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, parentDeclaration) { KoTypeDeclarationImpl(ktTypeReference) }
    }
}

package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationCore
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoParentProviderCore :
    KoParentProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override val parents: List<KoParentDeclaration>
        get() = ktClassOrObject
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationCore.getInstance(it, this) }
            ?: emptyList()

    override val numParents: Int
        get() = parents.size

    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> parents.isNotEmpty()
        else -> names.all {
            parents.any { parent -> it == parent.name }
        }
    }
}

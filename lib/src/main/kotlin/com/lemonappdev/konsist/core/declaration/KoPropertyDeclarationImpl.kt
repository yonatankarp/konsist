package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoVarAndValProviderCore
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoPropertyDeclarationImpl private constructor(override val ktProperty: KtProperty, parentDeclaration: KoParentProvider?) :
    KoPropertyDeclaration,
    KoBaseDeclarationImpl(ktProperty),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoVarAndValProviderCore,
    KoExtensionProviderCore,
    KoReceiverTypeProviderCore,
    KoDelegateProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktProperty

    override val ktCallableDeclaration: KtCallableDeclaration
        get() = ktProperty

    override val delegateName: String? by lazy {
        ktProperty
            .delegateExpression
            ?.text
            ?.replace("\n", " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    override val type: KoTypeDeclaration? by lazy { ReceiverUtil.getType(getTypeReferences(), isExtension(), this) }

    private fun getTypeReferences(): List<KtTypeReference> = ktProperty
        .children
        .filterIsInstance<KtTypeReference>()

    override fun hasType(type: String?): Boolean = when (type) {
        null -> this.type != null
        else -> this.type?.name == type
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPropertyDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktProperty: KtProperty, parentDeclaration: KoParentProvider?): KoPropertyDeclaration =
            cache.getOrCreateInstance(ktProperty, parentDeclaration) {
                KoPropertyDeclarationImpl(ktProperty, parentDeclaration)
            }
    }
}

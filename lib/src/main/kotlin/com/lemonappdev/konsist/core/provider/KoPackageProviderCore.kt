package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import org.jetbrains.kotlin.psi.KtFile

internal interface KoPackageProviderCore : KoPackageProvider, KoContainingFileProviderCore, KoBaseProviderCore {
    val ktFile: KtFile?

    override val packagee: KoPackageDeclaration?
        get() = if (ktFile == null) {
            containingFile.packagee
        } else if (ktFile?.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile?.packageDirective?.let { KoPackageDeclarationImpl.getInstance(it, null) }
        }
}
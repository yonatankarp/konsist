package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

fun Sequence<KoPackageDeclaration>.withQualifiedName(vararg names: String) = filter {
    names.any { name -> it.qualifiedName == name }
}

fun Sequence<KoPackageDeclaration>.withoutQualifiedName(vararg names: String) = filter {
    names.none { name -> it.qualifiedName == name }
}
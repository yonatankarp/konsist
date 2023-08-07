package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * List containing elements with an alias.
 *
 * @param names The names of aliases to include.
 * @return A list containing elements with the specified aliases (or any alias if [names] is empty).
 */
fun <T> List<T>.withAlias(vararg names: String): List<T>
    where T : KoAliasProvider,
          T : KoNameProvider = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

/**
 * List containing elements without an alias.
 *
 * @param names The names of aliases to exclude.
 * @return A list containing elements without specified aliases (or none alias if [names] is empty).
 */
fun <T> List<T>.withoutAlias(vararg names: String): List<T>
    where T : KoAliasProvider,
          T : KoNameProvider = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}
package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

/**
 * An interface representing a Kotlin scope, file, or declaration that provides information about objects.
 */
interface KoObjectProvider : KoBaseProvider {
    /**
     * The objects present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested objects.
     * @return a list of [KoObjectDeclaration] representing the objects in the scope, file or declaration.
     */
    fun objects(
        includeNested: Boolean = false,
    ): List<KoObjectDeclaration>

    /**
     * Checks whether the scope, file or declaration contains an object with the specified name.
     *
     * @param name The name of the object to check.
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `false`).
     * @return `true` if the scope, file, or declaration contains an object with the specified name, `false` otherwise.
     */
    fun containsObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    /**
     * Gets the number of objects present in the scope, file, or declaration.
     *
     * @param includeNested Specifies whether to include nested objects in the count (optional, default is `false`).
     * @return The number of objects in the scope, file, or declaration.
     */
    fun numObjects(includeNested: Boolean = false): Int
}

package net.bald.alias

import net.bald.Var
import net.bald.context.AliasDefinition

/**
 * Decorator for [Var] which supports attribute aliasing.
 */
class AliasVar(
    private val v: Var,
    alias: AliasDefinition
): AliasAttributeSource(v, alias), Var {
    override val uri: String get() = v.uri
}
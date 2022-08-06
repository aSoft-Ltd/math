package math.generator

open class SpacialGeneratorExtension {
    internal val immutableDefs = mutableListOf<SpacialDef>()
    internal val mutableDefs = mutableListOf<SpacialDef>()

    fun mutable(iFace: String, imp: String, hasZ: Boolean) {
        mutableDefs.add(SpacialDef(iFace, imp, hasZ))
    }

    fun immutable(iFace: String, imp: String, hasZ: Boolean) {
        immutableDefs.add(SpacialDef(iFace, imp, hasZ))
    }
}
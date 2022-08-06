package math.generator

open class SpacialGeneratorExtension {
    internal val immutableDefs = mutableListOf<SpatialDef>()
    internal val mutableDefs = mutableListOf<SpatialDef>()
    var interfaces = SpacialInterfaces("", "")
    var sPackage = "spatial"

    fun subpackage(name: String) {
        sPackage = name
    }

    fun interfaces(xy: String, xyz: String) {
        interfaces = SpacialInterfaces(xy, xyz)
    }

    fun mutable(iFace: String, imp: String, hasZ: Boolean) {
        mutableDefs.add(SpatialDef(iFace, imp, hasZ))
    }

    fun immutable(iFace: String, imp: String, hasZ: Boolean) {
        immutableDefs.add(SpatialDef(iFace, imp, hasZ))
    }
}
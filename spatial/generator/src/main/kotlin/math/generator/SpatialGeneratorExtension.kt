package math.generator

open class SpatialGeneratorExtension {
    internal val immutableDefs = mutableListOf<SpatialDef>()
    internal val mutableDefs = mutableListOf<SpatialDef>()
    var interfaces = SpatialInterfaces("", "")
    var sPackage = "spatial"

    fun subpackage(name: String) {
        sPackage = name
    }

    fun interfaces(xy: String, xyz: String) {
        interfaces = SpatialInterfaces(xy, xyz)
    }

    fun mutable(iFace: String, imp: String, hasZ: Boolean) {
        mutableDefs.add(SpatialDef(iFace, imp, hasZ))
    }

    fun immutable(iFace: String, imp: String, hasZ: Boolean) {
        immutableDefs.add(SpatialDef(iFace, imp, hasZ))
    }
}
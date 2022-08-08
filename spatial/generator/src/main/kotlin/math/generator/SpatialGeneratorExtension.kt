package math.generator

import java.io.Serializable

open class SpatialGeneratorExtension : Serializable {
    var interfaces = SpatialInterfaces("", "")
    var sPackage = "spatial"

    fun subpackage(name: String) {
        sPackage = name
    }

    fun interfaces(xy: String, xyz: String) {
        interfaces = SpatialInterfaces(xy, xyz)
    }
}
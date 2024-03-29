package math.generator

import java.io.Serializable

class SpatialInterfaces(
    val xy: String,
    val xyz: String
) : Serializable {
    fun toAllDefs() = toMutableDefs() + toImmutableDefs()

    fun toMutableDefs() = mutableListOf<SpatialDef>().apply {
        add(SpatialDef("Mutable$xy", "Mutable${xy}Impl", false))
        add(SpatialDef("Mutable$xyz", "Mutable${xyz}Impl", true))
    }.toList()

    fun toImmutableDefs() = mutableListOf<SpatialDef>().apply {
        add(SpatialDef(xy, "Mutable${xy}Impl", false))
        add(SpatialDef(xyz, "Mutable${xyz}Impl", true))
    }.toList()
}
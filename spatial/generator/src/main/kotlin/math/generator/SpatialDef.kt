package math.generator

data class SpatialDef(
    val iFace: String,
    val imp: String,
    val hasZ: Boolean,
) {
    companion object {
        fun from(name: String, hasZ: Boolean) = SpatialDef(name, "Mutable${name}Imp", hasZ)
        fun fromXY(name: String) = from(name, false)

        fun fromXYZ(name: String) = from(name, true)
    }

    val hasX = true
    val hasY = true

    val mutable get() = SpatialDef("Mutable$iFace", imp, hasZ)
}
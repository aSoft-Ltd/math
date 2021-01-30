package tz.co.asoft

data class Vec<N : Number>(
    val x: N,
    val y: N,
    val z: N = 0.0 as N
) {
    companion object {
        val ZERO = Vec(0, 0, 0)
    }

    override fun toString(): String = "Vec(x=${x.toReadableString()},y=${y.toReadableString()},z=${z.toReadableString()})"
    override fun equals(other: Any?): Boolean = when (other) {
        is Vec<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && z.isEqualTo(other.z)
        else -> false
    }
}
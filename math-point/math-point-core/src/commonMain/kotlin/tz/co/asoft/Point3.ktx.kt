package tz.co.asoft

fun <N : Number> Point3(x: N, y: N, z: N): Point3<N> = object : Point3<N> {
    override val x: N = x
    override val y: N = y
    override val z: N = z
    override fun toString(): String = "Point(x=${x.toReadableString()},y=${y.toReadableString()},z=${z.toReadableString()})"
    override fun equals(other: Any?): Boolean = when (other) {
        is Point3<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && z.isEqualTo(other.z)
        is Point2<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && z.isZero()
        else -> false
    }
}

fun <T : Number, N : Number> Point3<N>.copy(
    x: T = this.x as T,
    y: T = this.y as T,
    z: T = this.z as T
) = Point3(x, y, z)

operator fun <N : Number> Point3<N>.component3(): N = z
fun <N : Number> Point3<N>.cordString() = "(${x.toReadableString()},${y.toReadableString()},${z.toReadableString()})"
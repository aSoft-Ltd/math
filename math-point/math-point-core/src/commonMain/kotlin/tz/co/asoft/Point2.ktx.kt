package tz.co.asoft

fun <N : Number> Point2(x: N, y: N): Point2<N> = object : Point2<N> {
    override val x: N = x
    override val y: N = y
    override fun toString(): String = "Point(x=${x.toReadableString()},y=${y.toReadableString()})"
    override fun equals(other: Any?): Boolean = when (other) {
        is Point3<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && other.z.isZero()
        is Point2<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y)
        else -> false
    }
}

fun <T : Number, N : Number> Point2<N>.copy(x: T = this.x as T, y: T = this.y as T) = Point2(x, y)
operator fun <N : Number> Point2<N>.component1(): N = x
operator fun <N : Number> Point2<N>.component2(): N = y
fun Point2<*>.cordString() = "(${x.toReadableString()},${y.toReadableString()})"
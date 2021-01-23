package tz.co.asoft

import kotlin.math.abs
import kotlin.math.sqrt

fun <N : Number> Vec3(x: N, y: N, z: N): Vec3<N> = object : Vec3<N> {
    override val x: N = x
    override val y: N = y
    override val z: N = z
    override fun toString(): String = "Vec(x=${x.toReadableString()},y=${y.toReadableString()},z=${z.toReadableString()})"
    override fun equals(other: Any?): Boolean = when (other) {
        is Vec3<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && z.isEqualTo(other.z)
        is Vec2<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && z.isZero()
        else -> false
    }
}

fun <T : Number, N : Number> Vec3<N>.copy(
    x: T = this.x as T,
    y: T = this.y as T,
    z: T = this.z as T
) = Vec3(x, y, z)

operator fun <N : Number> Vec3<N>.component3(): N = z

fun <N : Number> Vec3<N>.vecString(): String {
    val sX = x.toReadableString(emptyIfOne = true)
    val sY = y.signString() + abs(y.toDouble()).toReadableString(emptyIfOne = true)
    val sZ = z.signString() + abs(z.toDouble()).toReadableString(emptyIfOne = true)
    return "${sX}i${sY}j${sZ}k"
}

fun Vec3<*>.squareMagnitude() = (x.toDouble() * x.toDouble()) + (y.toDouble() * y.toDouble()) + (z.toDouble() * z.toDouble())

fun Vec3<*>.magnitude() = sqrt(squareMagnitude())

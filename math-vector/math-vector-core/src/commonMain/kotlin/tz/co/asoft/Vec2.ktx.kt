package tz.co.asoft

import kotlin.math.abs
import kotlin.math.sqrt

fun <N : Number> Vec2(x: N, y: N): Vec2<N> = object : Vec2<N> {
    override val x: N = x
    override val y: N = y
    override fun toString(): String = "Vec(x=${x.toReadableString()},y=${y.toReadableString()})"
    override fun equals(other: Any?): Boolean = when (other) {
        is Vec3<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y) && other.z.isZero()
        is Vec2<*> -> x.isEqualTo(other.x) && y.isEqualTo(other.y)
        else -> false
    }
}

fun <T : Number, N : Number> Vec2<N>.copy(x: T = this.x as T, y: T = this.y as T) = Vec2(x, y)
operator fun <N : Number> Vec2<N>.component1(): N = x
operator fun <N : Number> Vec2<N>.component2(): N = y

fun Vec2<*>.vecString(): String {
    val sX = x.toReadableString(emptyIfOne = true)
    val sY = y.signString() + abs(y.toDouble()).toReadableString(emptyIfOne = true)
    return "${sX}i${sY}j"
}

fun Vec2<*>.squareMagnitude() = (x.toDouble() * x.toDouble()) + (y.toDouble() * y.toDouble())

fun Vec2<*>.magnitude() = sqrt(squareMagnitude())
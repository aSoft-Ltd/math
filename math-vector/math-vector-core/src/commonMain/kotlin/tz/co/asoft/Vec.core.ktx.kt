package tz.co.asoft

import kotlin.math.abs
import kotlin.math.sqrt

typealias V = Vec<*>

fun <N : Number> V(x: N, y: N, z: N = 0 as N): Vec<N> = Vec(x, y, z)

object Vector {
    val i = Vec(1, 0, 0)
    val j = Vec(0, 1, 0)
    val k = Vec(0, 0, 1)
}

fun <T : Number, N : Number> Vec<N>.copy(
    x: T = this.x as T,
    y: T = this.y as T,
    z: T = this.z as T
) = Vec(x, y, z)

fun Vec<*>.vecString(): String {
    val sX = x.toReadableString(emptyIfOne = true)
    val sY = y.signString() + abs(y.toDouble()).toReadableString(emptyIfOne = true)
    val sZ = z.signString() + abs(z.toDouble()).toReadableString(emptyIfOne = true)
    return "${sX}i${sY}j${sZ}k"
}

operator fun Vec<*>.unaryMinus() = Vec(-x.toDouble(), -y.toDouble(), -z.toDouble())

fun Vec<*>.squareMagnitude() = (x.toDouble() * x.toDouble()) + (y.toDouble() * y.toDouble()) + (z.toDouble() * z.toDouble())

fun Vec<*>.magnitude() = sqrt(squareMagnitude())

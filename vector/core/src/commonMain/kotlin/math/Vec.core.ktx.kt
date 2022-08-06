package math

import kotlin.math.abs
import kotlin.math.sqrt

typealias V = Vec<*>

object Vector {
    val i = Vec(1, 0, 0)
    val j = Vec(0, 1, 0)
    val k = Vec(0, 0, 1)
}

operator fun Vec<*>.unaryMinus() = Vec(-x.toDouble(), -y.toDouble(), -z.toDouble())

fun Vec<*>.squareMagnitude() = (x.toDouble() * x.toDouble()) + (y.toDouble() * y.toDouble()) + (z.toDouble() * z.toDouble())

fun Vec<*>.magnitude() = sqrt(squareMagnitude())

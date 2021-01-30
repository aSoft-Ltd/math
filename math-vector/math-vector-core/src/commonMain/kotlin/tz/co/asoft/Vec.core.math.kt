package tz.co.asoft

import kotlin.math.sqrt

operator fun Vec<*>.plus(p: Vec<*>) = Vec(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble() + p.z.toDouble()
)

operator fun Vec<*>.minus(p: Vec<*>) = Vec(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble(),
    z = z.toDouble() - p.z.toDouble()
)

operator fun Number.times(p: Vec<*>) = Vec(
    x = toDouble() * p.x.toDouble(),
    y = toDouble() * p.y.toDouble(),
    z = toDouble() * p.z.toDouble()
)

operator fun Vec<*>.times(n: Number) = n * this

fun Vec<*>.squareDistanceTo(v: Vec<*>) = (this - v).squareMagnitude()

fun Vec<*>.distanceTo(v: Vec<*>) = sqrt(squareDistanceTo(v))

infix fun Vec<*>.dot(other: Vec<*>) = x.toDouble() * other.x.toDouble() + y.toDouble() * other.y.toDouble() + z.toDouble() * other.z.toDouble()

operator fun Vec<*>.times(other: Vec<*>) = this dot other

/**
 * (x1,y1,z1)x(x2,y2,z2)
 *  i   j    k
 *  x1  y1  z1
 *  x2  y2  z2
 *  i(y1z2-y2z1)-j(x1z2-x2z1)+k(x1y2-y1x2)
 */
infix fun Vec<*>.cross(other: Vec<*>): Vec<Double> {
    val i = y.toDouble() * other.z.toDouble() - z.toDouble() * other.y.toDouble()
    val j = z.toDouble() * other.x.toDouble() - x.toDouble() * other.z.toDouble()
    val k = x.toDouble() * other.y.toDouble() - y.toDouble() * other.x.toDouble()
    return Vec(i, j, k)
}

infix fun Vec<*>.x(other: Vec<*>) = this cross other
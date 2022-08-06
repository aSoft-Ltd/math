package math.vector

import math.Vec

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
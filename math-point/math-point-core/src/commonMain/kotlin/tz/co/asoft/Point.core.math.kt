package tz.co.asoft

import kotlin.math.sqrt

operator fun Point<*>.plus(p: Point<*>) = Point(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble() + p.z.toDouble()
)

operator fun Point<*>.minus(p: Point<*>) = Point(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble(),
    z = z.toDouble() - p.z.toDouble()
)

operator fun Number.times(p: Point<*>) = Point(
    x = toDouble() * p.x.toDouble(),
    y = toDouble() * p.y.toDouble(),
    z = toDouble() * p.z.toDouble()
)

fun divide(p1: Point<*>, p2: Point<*>, m: Int, n: Int): Point<Double> {
    val total = m + n
    val x = ((m * p1.x.toDouble()) + (n * p2.x.toDouble())) / total
    val y = ((m * p1.y.toDouble()) + (n * p2.y.toDouble())) / total
    val z = ((m * p1.z.toDouble()) + (n * p2.z.toDouble())) / total
    return Point(x, y, z)
}

fun Point<*>.squareDistanceTo(p: Point<*>): Double {
    val dX = x.toDouble() - p.x.toDouble()
    val dY = y.toDouble() - p.y.toDouble()
    val dZ = z.toDouble() - p.z.toDouble()
    return (dX * dX) + (dY * dY) + (dZ * dZ)
}

fun Point<*>.distanceTo(p: Point<*>) = sqrt(squareDistanceTo(p))
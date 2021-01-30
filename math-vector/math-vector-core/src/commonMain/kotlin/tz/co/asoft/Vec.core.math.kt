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


fun Vec<*>.squareDistanceTo(v: Vec<*>) = (this - v).squareMagnitude()

fun Vec<*>.distanceTo(v: Vec<*>) = sqrt(squareDistanceTo(v))
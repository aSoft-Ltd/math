package tz.co.asoft

import kotlin.math.sqrt

fun Point2<*>.squareDistanceTo(p: Point2<*>): Double {
    val dX = x.toDouble() - p.x.toDouble()
    val dY = y.toDouble() - p.y.toDouble()
    return (dX * dX) + (dY * dY)
}

fun Point2<*>.distanceTo(p: Point2<*>) = sqrt(squareDistanceTo(p))

fun Point2<*>.squareDistanceTo(p: Point3<*>): Double {
    val dX = x.toDouble() - p.x.toDouble()
    val dY = y.toDouble() - p.y.toDouble()
    val dZ = p.z.toDouble()
    return (dX * dX) + (dY * dY) + (dZ * dZ)
}

fun Point2<*>.distanceTo(p: Point3<*>) = sqrt(squareDistanceTo(p))

fun Point3<*>.squareDistanceTo(p: Point2<*>): Double {
    val dX = x.toDouble() - p.x.toDouble()
    val dY = y.toDouble() - p.y.toDouble()
    val dZ = z.toDouble()
    return (dX * dX) + (dY * dY) + (dZ * dZ)
}

fun Point3<*>.distanceTo(p: Point2<*>) = sqrt(squareDistanceTo(p))

fun Point3<*>.squareDistanceTo(p: Point3<*>): Double {
    val dX = x.toDouble() - p.x.toDouble()
    val dY = y.toDouble() - p.y.toDouble()
    val dZ = z.toDouble() - p.z.toDouble()
    return (dX * dX) + (dY * dY) + (dZ * dZ)
}

fun Point3<*>.distanceTo(p: Point3<*>) = sqrt(squareDistanceTo(p))
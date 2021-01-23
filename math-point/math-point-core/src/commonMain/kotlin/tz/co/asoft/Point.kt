package tz.co.asoft

fun <N : Number> Point(x: N, y: N, z: N? = null) = Point3(
    x = x,
    y = y,
    z = z ?: 0
)

operator fun Point2<*>.plus(p: Point3<*>) = Point3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = p.z.toDouble()
)

operator fun Point3<*>.plus(p: Point2<*>) = Point3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble()
)

operator fun Point2<*>.minus(p: Point3<*>) = Point3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = p.z.toDouble()
)

operator fun Point3<*>.minus(p: Point2<*>) = Point3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble()
)

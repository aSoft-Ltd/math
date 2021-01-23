package tz.co.asoft

operator fun Point3<*>.plus(p: Point3<*>) = Point3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble() + p.z.toDouble()
)

operator fun Point3<*>.minus(p: Point3<*>) = Point3(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble(),
    z = z.toDouble() - p.z.toDouble()
)

operator fun Number.times(p: Point3<*>) = Point3(
    x = toDouble() * p.x.toDouble(),
    y = toDouble() * p.y.toDouble(),
    z = toDouble() * p.z.toDouble()
)
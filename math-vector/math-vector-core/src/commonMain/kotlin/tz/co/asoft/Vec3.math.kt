package tz.co.asoft

operator fun Vec3<*>.plus(p: Vec3<*>) = Vec3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble() + p.z.toDouble()
)

operator fun Vec3<*>.minus(p: Vec3<*>) = Vec3(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble(),
    z = z.toDouble() - p.z.toDouble()
)

operator fun Number.times(p: Vec3<*>) = Vec3(
    x = toDouble() * p.x.toDouble(),
    y = toDouble() * p.y.toDouble(),
    z = toDouble() * p.z.toDouble()
)
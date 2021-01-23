package tz.co.asoft

fun <N : Number> Vec(x: N, y: N, z: N? = null) = Vec3(
    x = x,
    y = y,
    z = z ?: 0
)

operator fun Vec2<*>.plus(p: Vec3<*>) = Vec3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = p.z.toDouble()
)

operator fun Vec3<*>.plus(p: Vec2<*>) = Vec3(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble(),
    z = z.toDouble()
)

operator fun Vec2<*>.minus(p: Vec3<*>) = Vec3(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble(),
    z = -p.z.toDouble()
)

operator fun Vec3<*>.minus(p: Vec2<*>) = Vec3(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble(),
    z = z.toDouble()
)

object Vec {
    val i = Vec2(1, 0)
    val j = Vec2(0, 1)
    val k = Vec3(0, 0, 1)
}
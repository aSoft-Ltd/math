package tz.co.asoft

operator fun Vec2<*>.plus(p: Vec2<*>) = Vec2(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble()
)

operator fun Vec2<*>.minus(p: Vec2<*>) = Vec2(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble()
)

operator fun Number.times(p: Vec2<*>) = Vec2(
    x = toDouble() * p.x.toDouble(),
    y = toDouble() * p.y.toDouble()
)
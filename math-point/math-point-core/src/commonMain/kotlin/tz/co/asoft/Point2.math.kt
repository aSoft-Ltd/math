package tz.co.asoft

operator fun Point2<*>.plus(p: Point2<*>) = Point2(
    x = x.toDouble() + p.x.toDouble(),
    y = y.toDouble() + p.y.toDouble()
)

operator fun Point2<*>.minus(p: Point2<*>) = Point2(
    x = x.toDouble() - p.x.toDouble(),
    y = y.toDouble() - p.y.toDouble()
)

operator fun Number.times(p: Point2<*>) = Point2(
    x = toDouble() * p.x.toDouble(),
    y = toDouble() * p.y.toDouble()
)
package math.point

import math.Point2
import math.lerp
import math.Point3

fun <N : Number> lerp(a: Point3<N>, b: Point3<N>, t: Double) = Point3(
    x = lerp(a.x, b.x, t),
    y = lerp(a.y, b.y, t),
    z = lerp(a.z, b.z, t)
)

fun <N : Number> lerp(a: Point2<N>, b: Point2<N>, t: Double) = Point2(
    x = lerp(a.x, b.x, t),
    y = lerp(a.y, b.y, t)
)
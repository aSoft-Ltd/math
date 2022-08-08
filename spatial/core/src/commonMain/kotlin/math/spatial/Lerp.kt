package math.spatial

import math.XY
import math.lerp
import math.XYZ

fun <N : Number> lerp(a: XYZ<N>, b: XYZ<N>, t: Double) = XYZ(
    x = lerp(a.x, b.x, t),
    y = lerp(a.y, b.y, t),
    z = lerp(a.z, b.z, t)
)

fun <N : Number> lerp(a: XY<N>, b: XY<N>, t: Double) = XY(
    x = lerp(a.x, b.x, t),
    y = lerp(a.y, b.y, t)
)
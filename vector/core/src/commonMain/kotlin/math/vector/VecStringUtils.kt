package math.vector

import math.Vec2
import math.Vec3
import math.signString
import math.toReadableString
import kotlin.math.abs

fun <N : Number> Vec3<N>.vecString(): String {
    val sX = x.toReadableString(emptyIfOne = true)
    val sY = y.signString() + abs(y.toDouble()).toReadableString(emptyIfOne = true)
    val sZ = z.signString() + abs(z.toDouble()).toReadableString(emptyIfOne = true)
    return "${sX}i${sY}j${sZ}k"
}

fun <N : Number> Vec2<N>.vecString(): String {
    val sX = x.toReadableString(emptyIfOne = true)
    val sY = y.signString() + abs(y.toDouble()).toReadableString(emptyIfOne = true)
    return "${sX}i${sY}j"
}
package tz.co.asoft


fun <N : Number> P(x: N, y: N, z: N = 0.0 as N): Point<N> = Point(x, y, z)

fun <T : Number, N : Number> Point<N>.copy(
    x: T = this.x as T,
    y: T = this.y as T,
    z: T = this.z as T
) = Point(x, y, z)

fun <N : Number> Point<N>.cordString() = "(${x.toReadableString()},${y.toReadableString()},${z.toReadableString()})"
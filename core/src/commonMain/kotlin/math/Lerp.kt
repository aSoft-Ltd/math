package math

/**
 * Linear interpolation between point a and b
 * @param a is the starting point
 *
 * @param b is the endpoint
 *
 * @param t is the interpolation ration
 */
fun lerp(a: Double, b: Double, t: Double): Double = a + (b - a) * t

/**
 * Linear interpolation between point a and b
 * @param a is the starting point
 *
 * @param b is the endpoint
 *
 * @param t is the interpolation ration
 */
fun lerp(a: Number, b: Number, t: Double) = math.lerp(a.toDouble(), b.toDouble(), t)
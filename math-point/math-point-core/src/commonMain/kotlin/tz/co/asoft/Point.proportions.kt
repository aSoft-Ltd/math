package tz.co.asoft

fun divide(p1: Point2<*>, p2: Point2<*>, m: Int, n: Int): Point2<Double> {
    val total = m + n
    val x = ((m * p1.x.toDouble()) + (n * p2.x.toDouble())) / total
    val y = ((m * p1.y.toDouble()) + (n * p2.y.toDouble())) / total
    return Point2(x, y)
}

fun midPointOf(p1: Point2<*>, p2: Point2<*>) = divide(p1, p2, 1, 1)

fun divide(p1: Point3<*>, p2: Point2<*>, m: Int, n: Int): Point3<Double> {
    val total = m + n
    val x = ((m * p1.x.toDouble()) + (n * p2.x.toDouble())) / total
    val y = ((m * p1.y.toDouble()) + (n * p2.y.toDouble())) / total
    val z = (m * p1.z.toDouble()) / total
    return Point3(x, y, z)
}

fun midPointOf(p1: Point3<*>, p2: Point2<*>) = divide(p1, p2, 1, 1)

fun divide(p1: Point3<*>, p2: Point3<*>, m: Int, n: Int): Point3<Double> {
    val total = m + n
    val x = ((m * p1.x.toDouble()) + (n * p2.x.toDouble())) / total
    val y = ((m * p1.y.toDouble()) + (n * p2.y.toDouble())) / total
    val z = ((m * p1.z.toDouble()) + (n * p2.z.toDouble())) / total
    return Point3(x, y, z)
}

fun midPointOf(p1: Point3<*>, p2: Point3<*>) = divide(p1, p2, 1, 1)
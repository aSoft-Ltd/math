package tz.co.asoft

interface Point3<N : Number> : Point2<N> {
    val z: N

    companion object {
        val origin = Point3(0, 0, 0)
    }
}
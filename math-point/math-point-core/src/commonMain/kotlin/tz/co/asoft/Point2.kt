package tz.co.asoft

interface Point2<N : Number> {
    val x: N
    val y: N
    companion object {
        val origin = Point2(0, 0)
    }
}
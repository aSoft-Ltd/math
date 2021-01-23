package tz.co.asoft

interface Vec2<N : Number> {
    val x: N
    val y: N
    companion object {
        val origin = Vec2(0, 0)
    }
}
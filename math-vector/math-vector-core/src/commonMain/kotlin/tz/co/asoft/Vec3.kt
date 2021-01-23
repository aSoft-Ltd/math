package tz.co.asoft

interface Vec3<N : Number> : Vec2<N> {
    val z: N

    companion object {
        val origin = Vec3(0, 0, 0)
    }
}
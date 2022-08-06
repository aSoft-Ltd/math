package math.internal

import math.MutablePoint3

@PublishedApi
internal data class MutablePoint3Impl<N>(
    override var x: N,
    override var y: N,
    override var z: N
) : MutablePoint3<N> {
    override fun toString() = "Point(x=$x,y=$y,z=$z)"
}
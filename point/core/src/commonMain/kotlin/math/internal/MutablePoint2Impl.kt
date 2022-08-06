package math.internal

import math.MutablePoint2

@PublishedApi
internal data class MutablePoint2Impl<N>(
    override var x: N,
    override var y: N
) : MutablePoint2<N> {
    override fun toString() = "Point(x=$x,y=$y)"
}
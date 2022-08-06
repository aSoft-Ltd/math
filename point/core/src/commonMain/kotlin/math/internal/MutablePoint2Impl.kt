package math.internal

import math.MutablePoint2

internal data class MutablePoint2Impl<N : Number>(
    override var x: N,
    override var y: N
) : MutablePoint2<N>
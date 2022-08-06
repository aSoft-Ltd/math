package math.internal

import math.MutablePoint3

internal data class MutablePoint3Impl<N : Number>(
    override var x: N,
    override var y: N,
    override var z: N
) : MutablePoint3<N>
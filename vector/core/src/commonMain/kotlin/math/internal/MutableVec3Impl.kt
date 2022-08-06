package math.internal

import math.MutableVec3

@PublishedApi
internal class MutableVec3Impl<N>(
    override var x: N,
    override var y: N,
    override var z: N
) : MutableVec3<N>
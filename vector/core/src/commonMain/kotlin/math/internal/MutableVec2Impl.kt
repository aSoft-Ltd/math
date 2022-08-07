package math.internal

import math.MutableVec2

@PublishedApi
internal data class MutableVec2Impl<N>(
    override var x: N,
    override var y: N
) : MutableVec2<N>
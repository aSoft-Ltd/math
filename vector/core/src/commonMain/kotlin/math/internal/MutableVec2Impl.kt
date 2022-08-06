package math.internal

import math.MutableVec2

@PublishedApi
internal class MutableVec2Impl<N>(
    override var x: N,
    override var y: N
) : MutableVec2<N>
package math.internal

import math.MutableVec3

class MutableVec3Impl<N>(
    override var x: N,
    override var y: N,
    override var z: N
) : MutableVec3<N>
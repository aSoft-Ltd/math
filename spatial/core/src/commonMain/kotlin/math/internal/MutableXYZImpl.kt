package math.internal

import math.MutableXYZ

class MutableXYZImpl<N>(
    override var x: N,
    override var y: N,
    override var z: N
) : MutableXYZ<N>
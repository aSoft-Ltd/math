package math.internal

import math.MutableXY

class MutableXYImpl<N>(
    override var x: N,
    override var y: N
) : MutableXY<N>
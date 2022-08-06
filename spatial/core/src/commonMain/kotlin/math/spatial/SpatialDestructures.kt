package math.spatial

import math.XY
import math.XYZ

inline operator fun <N> XY<N>.component1() = x

inline operator fun <N> XY<N>.component2() = y

inline operator fun <N> XYZ<N>.component3() = z
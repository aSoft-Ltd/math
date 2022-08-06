package math.point

import math.Point2
import math.Point3

inline operator fun <N> Point2<N>.component1() = x

inline operator fun <N> Point2<N>.component2() = y

inline operator fun <N> Point3<N>.component3() = z
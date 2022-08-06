package math.point

import math.Point2
import math.Point3
import math.toReadableString

fun <N : Number> Point2<N>.cordString() = "(${x.toReadableString()},${y.toReadableString()})"

fun <N : Number> Point3<N>.cordString() = "(${x.toReadableString()},${y.toReadableString()},${z.toReadableString()})"
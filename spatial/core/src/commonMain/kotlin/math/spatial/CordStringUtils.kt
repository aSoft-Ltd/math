package math.spatial

import math.XY
import math.XYZ
import math.toReadableString

fun <N : Number> XY<N>.cordString() = "(${x.toReadableString()},${y.toReadableString()})"

fun <N : Number> XYZ<N>.cordString() = "(${x.toReadableString()},${y.toReadableString()},${z.toReadableString()})"
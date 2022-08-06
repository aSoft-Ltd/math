package math.spatial

import math.MutableXY
import math.MutableXYZ
import math.XY
import math.XYZ
import math.internal.MutableXYImpl
import math.internal.MutableXYZImpl

inline fun <N, R> MutableXYZ<N>.map(transformer: (N) -> R): MutableXYZ<R> = MutableXYZImpl(transformer(x), transformer(y), transformer(z))

inline fun <N, R> MutableXY<N>.map(transformer: (N) -> R): MutableXY<R> = MutableXYImpl(transformer(x), transformer(y))

inline fun <N, R> XYZ<N>.map(transformer: (N) -> R): XYZ<R> = MutableXYZImpl(transformer(x), transformer(y), transformer(z))

inline fun <N, R> XY<N>.map(transformer: (N) -> R): XY<R> = MutableXYImpl(transformer(x), transformer(y))
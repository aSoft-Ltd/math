package math.point

import math.MutablePoint2
import math.MutablePoint3
import math.Point2
import math.Point3
import math.internal.MutablePoint2Impl
import math.internal.MutablePoint3Impl

inline fun <N, R> MutablePoint3<N>.map(transformer: (N) -> R): MutablePoint3<R> = MutablePoint3Impl(transformer(x), transformer(y), transformer(z))

inline fun <N, R> MutablePoint2<N>.map(transformer: (N) -> R): MutablePoint2<R> = MutablePoint2Impl(transformer(x), transformer(y))

inline fun <N, R> Point3<N>.map(transformer: (N) -> R): Point3<R> = MutablePoint3Impl(transformer(x), transformer(y), transformer(z))

inline fun <N, R> Point2<N>.map(transformer: (N) -> R): Point2<R> = MutablePoint2Impl(transformer(x), transformer(y))
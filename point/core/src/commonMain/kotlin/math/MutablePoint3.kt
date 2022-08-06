package math

import kotlin.js.JsExport

@JsExport
interface MutablePoint3<N> : Point3<N>, MutablePoint2<N>, MutableXYZ<N>
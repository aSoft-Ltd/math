package math

import kotlin.js.JsExport

@JsExport
interface Point3<out N> : Point2<N>, XYZ<N>
package math

import kotlin.js.JsExport

@JsExport
interface MutablePoint2<N> : Point2<N> {
    override var x: N
    override var y: N
}
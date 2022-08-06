package math

import kotlin.js.JsExport

@JsExport
interface Point2<out N> {
    val x: N
    val y: N
}
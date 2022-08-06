package math

import kotlin.js.JsExport

@JsExport
interface Z<out N> {
    val z: N
}
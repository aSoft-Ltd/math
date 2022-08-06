package math

import kotlin.js.JsExport

@JsExport
interface X<out N> {
    val x: N
}
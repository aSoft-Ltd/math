package math

import kotlin.js.JsExport

@JsExport
interface Y<out N> {
    val y: N
}
package math

import kotlin.js.JsExport

@JsExport
interface MutableY<N> : Y<N> {
    override var y: N
}
package math

import kotlin.js.JsExport

@JsExport
interface MutableZ<N> : Z<N> {
    override var z: N
}
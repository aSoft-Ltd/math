package math

import kotlin.js.JsExport

@JsExport
interface XYZ<out N> : XY<N>, Z<N>
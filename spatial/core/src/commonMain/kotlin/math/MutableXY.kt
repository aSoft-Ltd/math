package math

import kotlin.js.JsExport

@JsExport
interface MutableXY<N> : XY<N>, MutableX<N>, MutableY<N>